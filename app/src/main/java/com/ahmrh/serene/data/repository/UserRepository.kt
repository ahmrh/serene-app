package com.ahmrh.serene.data.repository

import android.net.Uri
import android.util.Log
import com.ahmrh.serene.common.enums.Language
import com.ahmrh.serene.common.enums.Sentiment
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.data.source.remote.response.SelfCareHistoryResponse
import com.ahmrh.serene.data.source.remote.response.UserResponse
import com.ahmrh.serene.data.source.remote.response.toMap
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.domain.model.user.Auth
import com.ahmrh.serene.domain.model.user.Profile
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.domain.model.user.toMap
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
) {

    private var language: String = Locale.getDefault().language

    fun getCurrentUser() = auth.currentUser

    fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {

        auth.signInAnonymously()
            .addOnCompleteListener {

                val user = auth.currentUser
                val profileUpdates = userProfileChangeRequest {
                    displayName = "Anon"
                }

                user?.updateProfile(profileUpdates)
                onResult(it.exception)
            }
    }

    fun authenticate(
        email: String, password: String, onResult: (Throwable?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun recoverPassword(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                onResult(task.exception)
            }

    }

    fun linkAccount(
        username: String, email: String, password: String,
        onResult: (Throwable?) -> Unit
    ) {
        val credential = EmailAuthProvider.getCredential(email, password)

        auth.currentUser!!.linkWithCredential(credential)
            .addOnSuccessListener {
                createAuthData(username, email, password, {})
            }
            .addOnCompleteListener { onResult(it.exception) }


    }


    fun createAccount(
        username: String, email: String, password: String,
        onResult: (Throwable?) -> Unit
    ) {
        val user = auth.currentUser
        if(user != null && user.isAnonymous){

            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential)
                .addOnSuccessListener {
                    createAuthData(username, email, password, {})

                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }

                    user.updateProfile(profileUpdates)
                }
                .addOnCompleteListener { onResult(it.exception) }
        } else {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult ->
                    createAuthData(username, email, password, {})
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }

                    authResult.user?.updateProfile(profileUpdates)?.addOnCompleteListener{
                        onResult(it.exception)
                    }

                }
                .addOnCompleteListener {
                    onResult(it.exception)
                }
        }

    }

    private fun createAuthData(
        username: String, email: String, password: String,
        onResult: (Throwable?) -> Unit
    ) {
        val userId = auth.currentUser?.uid ?: return

        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )

        val collectionReference = firestore.collection("users")

        collectionReference
            .document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(
                    PersonalizationRepository.TAG,
                    "DocumentSnapshot successfully written!"
                )
            }
            .addOnFailureListener { e ->
                Log.w(
                    PersonalizationRepository.TAG,
                    "Error writing document", e
                )
            }
            .addOnCompleteListener { onResult(it.exception) }

    }

    fun getAuthData(
        onSuccess: ( Auth ) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){

        try {

            val user = auth.currentUser ?: return

            val name = user.displayName
            val email = user.email
            val isAnon = user.isAnonymous

            onSuccess(Auth(name ?: "", "", email?:"", isAnon))
        } catch (e: Exception) {
            onFailure(e)

        }


    }

    fun updateAuthData(
        data: Auth,
        onResult: (Throwable?) -> Unit,
    ){
        val user = auth.currentUser ?: return

        val email = data.email
        val password = data.password
        val username = data.displayName

        val profileUpdates = userProfileChangeRequest {
            displayName = username
        }


        try{
            user.updatePassword(password)
            user.updateEmail(email)
            user.updateProfile(profileUpdates)

            onResult(null)

        } catch(e: Exception){
            onResult(e)
        }

    }

    suspend fun addSelfCareHistory(
        selfCareActivity: SelfCareActivity,
        sentiment: Sentiment,
        onResult: (Throwable?) -> Unit
    ) {
        val userId = auth.currentUser?.uid ?: return
        val date = Calendar.getInstance().time;

        val selfCareId = selfCareActivity.id
        val feedbackSentiment = sentiment.stringValue
        val selfCareCategory = selfCareActivity.category!!
        val selfCareName = selfCareActivity.name!!

        val selfCareHistory = SelfCareHistory(
            selfCareId,
            selfCareCategory,
            feedbackSentiment,
            selfCareName,
            date
        )

        val history = selfCareHistory.toMap()
        try {

            firestore.collection("users").document(userId)
                .collection("history")
                .add(history)
                .await()

            Log.d(
                TAG, "DocumentSnapshot successfully written!"
            )
        } catch(e: Exception){
            Log.w(
                TAG, "Error writing document", e
            )
            onResult(e)
        }

    }


    suspend fun fetchSelfCareHistoryList(): List<SelfCareHistory> {

        val userId = auth.currentUser?.uid ?: return listOf()

        val collectionReference = firestore.collection("users")
            .document(userId)
            .collection("history")


        try {

            val historyList = withContext(Dispatchers.IO) {

                val documents = collectionReference
                    .get()
                    .await()

                documents.map { data ->
                    val historyResponse =
                        data.toObject<SelfCareHistoryResponse>()

                    SelfCareHistory(
                        selfCareId = historyResponse.selfCareId!!,
                        selfCareCategory = historyResponse.selfCareCategory!!,
                        selfCareName = historyResponse.selfCareName!!,
                        date = Date(historyResponse.date!!),
                        feedbackSentiment = historyResponse.feedbackSentiment!!
                    )
                }
            }

            return historyList

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching self care history: $e")
        }

        return listOf()
    }

    suspend fun fetchAchievementsList(): List<Achievement>? {

        val userId = auth.currentUser?.uid ?: return listOf()

        val collectionReference = firestore.collection("users")
            .document(userId)
            .collection("achievements")


        try {

            val documents = collectionReference
                .get()
                .await()

            val achievementList = documents.map { data ->
                val achievementResponse =
                    data.toObject<AchievementResponse>()

                val imageRef = storage.reference.child(
                    "achievements/${achievementResponse.image}.png"
                )

                val imageUrl = imageRef.downloadUrl.await()

                Achievement(
                    id = data.id,
                    imageUri = imageUrl,
                    name = achievementResponse.name,
                    progress = achievementResponse.progress,
                    description =
                    if (language == Language.ID.code) achievementResponse.description?.id
                    else achievementResponse.description?.en,
                    category = achievementResponse.category
                )
            }

            return achievementList

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching self care history: $e")
        }

        return null
    }


//    suspend fun getDailyStreak(
//    ): Int{
//
//        val userId = auth.currentUser!!.uid
//
//        val dailyStreak = withContext(Dispatchers.IO){
//
//            val documents = firestore.collection("users")
//                .document(userId)
//                .collection("history")
//                .get()
//                .await()
//
//            val historyResponse = documents.map{data ->
//                data.toObject<SelfCareHistoryResponse>()
//            }
//
//
//            DateUtils.getDayStreak(historyResponse.map { Date(it.date!!) })
//        }
//
//        return dailyStreak;
//
//    }
//

    // TODO: test this out
    suspend fun getLatestPracticeDate(): Date? {
        try{

            val selfCareHistory = fetchSelfCareHistoryList() ?: return null

            val sortedHistory = selfCareHistory.sortedBy { it.date }

            val latestHistory = sortedHistory.first()

            return latestHistory.date
        } catch (e: Exception){
            return null
        }
    }

    fun getAccountCreatedDate(): Date? {
        return auth.currentUser?.metadata?.let {
            Date(
                it.creationTimestamp
            )
        }
    }

    suspend fun getTotalPracticedSelfCareByCategory(
        categoryString: String
    ): Int {

        val userId = auth.currentUser?.uid ?: return 0

        val collectionReference = firestore.collection("users")

        val documents = collectionReference
            .document(userId)
            .collection("history")
            .whereEqualTo("selfCareCategory", categoryString)
            .get()
            .await()

        return documents.size()
    }

    suspend fun addAchievementById(
        achievementId: String,
        onResult: (Throwable?) -> Unit
    ) {
        val achievementResponse = firestore.collection("achievements")
            .document(achievementId)
            .get()
            .await()
            .toObject<AchievementResponse>()!!

        val achievement = achievementResponse.toMap()

        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(userId)
            .collection("achievements")
            .document(achievementId)
            .set(achievement)
            .addOnCompleteListener {
                onResult(it.exception)
            }

    }

    fun fetchUsername(
        onSuccess: (String?) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {

        val userId = auth.currentUser?.uid ?: return
        val collectionReference = firestore.collection("users")

        collectionReference
            .document(userId)
            .get()

            .addOnSuccessListener { document ->
                val data = document.toObject<UserResponse>()

                onSuccess(data?.username)
            }
            .addOnFailureListener(onFailure)

    }

//    fun fetchSelfCareHistoryList(
//        onSuccess: (List<SelfCareHistory>) -> Unit,
//        onFailure: (Throwable?) -> Unit
//    ) {
//        val userId = auth.currentUser!!.uid ?: return
//
//        val collectionReference = firestore.collection("users")
//            .document(userId)
//            .collection("history")
//        collectionReference
//            .get()
//            .addOnSuccessListener { documents ->
//                val historyList =
//                    documents.map { data ->
//                        val selfCareHistoryResponse =
//                            data.toObject<SelfCareHistoryResponse>()
//
//                        SelfCareHistory(
//                            selfCareHistoryResponse.selfCareId!!,
//                            selfCareHistoryResponse.selfCareCategory!!,
//                            selfCareHistoryResponse.feedbackSentiment!!,
//                            selfCareHistoryResponse.selfCareName!!,
//                            Date(selfCareHistoryResponse.date!!),
//                        )
//                    }
//                val sortedHistoryList =
//                    historyList.sortedByDescending { it.date }
//
//                onSuccess(sortedHistoryList)
//
//            }
//            .addOnFailureListener(onFailure)
//    }
    suspend fun fetchUserAchievements(
        onSuccess: (List<Achievement>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        val userId = auth.currentUser?.uid ?: return
        val collectionReference = firestore.collection("users")

        try {
            val achievements = withContext(Dispatchers.IO) {
                val documents = collectionReference.document(userId)
                    .collection("achievements").get().await()

                val achievements = documents.map { data ->
                    val achievementResponse =
                        data.toObject<AchievementResponse>()

                    val imageRef = storage.reference.child(
                        "achievements/${achievementResponse.image}.png"
                    )

                    val imageUrl = imageRef.downloadUrl.await()

                    Achievement(
                        id = data.id,
                        imageUri = imageUrl,
                        name = achievementResponse.name,
                        progress = achievementResponse.progress,
                        description =
                        if (language == Language.ID.code) achievementResponse.description?.id
                        else achievementResponse.description?.en,
                        category = achievementResponse.category
                    )
                }

                achievements
            }

            onSuccess(achievements)
        } catch (e: Exception) {
            onFailure(e)
        }
    }



//    suspend fun fetchProfileData(
//        onSuccess: (Profile) -> Unit,
//         onFailure: (Throwable?) -> Unit
//    ) {
//
//        try {
//            val user = auth.currentUser!!
//            val userId = user.uid
//            Log.d(TAG, "user id = $userId")
//            Log.d(TAG, "isAnon = ${user?.isAnonymous}")
//
//            val profileData = withContext(Dispatchers.IO) {
//
////                val achievementDocuments = firestore.collection("users")
////                    .document(userId)
////                    .collection("achievements").get().await()
////
////                val achievementList = achievementDocuments.map { data ->
////                    val achievementResponse =
////                        data.toObject<AchievementResponse>()
////
////                    val imageRef = storage.reference.child(
////                        "achievements/${achievementResponse.image}.png"
////                    )
////
////                    val imageUrl = imageRef.downloadUrl.await()
////
////                    Achievement(
////                        id = data.id,
////                        imageUri = imageUrl,
////                        name = achievementResponse.name,
////                        progress = achievementResponse.progress,
////                        description =
////                        if (language == Language.ID.code) achievementResponse.description?.id
////                        else achievementResponse.description?.en,
////                        category = achievementResponse.category
////                    )
////                }
//
////                val historyDocuments = firestore.collection("users")
////                    .document(userId)
////                    .collection("history").get().await()
////
////                val historyList = historyDocuments.map { data ->
////
////                    val selfCareHistoryResponse =
////                        data.toObject<SelfCareHistoryResponse>()
////
////                    SelfCareHistory(
////                        selfCareHistoryResponse.selfCareId!!,
////                        selfCareHistoryResponse.selfCareCategory!!,
////                        selfCareHistoryResponse.feedbackSentiment
////                            ?: "Very Satisfied",
////                        selfCareHistoryResponse.selfCareName!!,
////                        Date(selfCareHistoryResponse.date!!),
////                    )
////                }
//
////                val sortedHistoryList =
////                    historyList.sortedByDescending { it.date }
//
//
//                val displayName = user.displayName
//
////                val dayStreak = DateUtils.getDayStreak(
////                    historyList.map {
////                        it.date
////                    }
////                )
////
////                val topSelfCare = ArrayUtils.getMaxOccurringString(
////                    historyList.map {
////                        it.selfCareCategory
////                    }
////                )
////
////                val totalSelfCare = historyList.size
////                val totalAchievement = achievementList.size
//                val signupDate = auth.currentUser?.metadata?.let {
//                    Date(
//                        it.creationTimestamp
//                    )
//                }
//
//                val isAnon = user.isAnonymous
//
//
//                Profile(
//                    displayName = if(isAnon) "Anon" else "$displayName",
//                    joined = signupDate ?: Date(1220227200),
//                    imgUri = Uri.parse(
//                        "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png"
//                    ),
//                    dayStreak = dayStreak ?: 0,
//                    topSelfCare = topSelfCare,
//                    totalAchievement = totalAchievement,
//                    totalSelfCare = totalSelfCare,
//                    achievementList = achievementList,
//                    historyList = sortedHistoryList,
//                    isAnon = isAnon
//                )
//            }
//
//
//            onSuccess(profileData)
//        } catch (e: Exception) {
//            onFailure(e)
//            throw (e)
//        }
//    }


    fun fetchProfileData(
        onSuccess: (Profile) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {

        val user = auth.currentUser ?: return

        try {

            val displayName = user.displayName

            val signupDate = auth.currentUser?.metadata?.let {
                Date(
                    it.creationTimestamp
                )
            }

            val isAnon = user.isAnonymous
            val email = user.email


            val profileData = Profile(
                displayName = displayName ?: "Anon",
                email = email ?: "Unregistered",
                joined = signupDate ?: Date(),
                imgUri = Uri.parse(
                    "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png"
                ),
                isAnon = isAnon,
            )


            onSuccess(profileData)
        } catch (e: Exception) {
            onFailure(e)
            throw (e)
        }
    }

    companion object {
        const val TAG = "UserRepository"
    }
}