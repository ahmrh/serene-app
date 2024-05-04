package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.enums.Language
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.data.source.remote.response.ChallengeResponse
import com.ahmrh.serene.data.source.remote.response.TodayChallengeResponse
import com.ahmrh.serene.data.source.remote.response.toChallenge
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.ahmrh.serene.domain.model.gamification.toMap
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Random
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamificationRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore,
    private val userRepository: UserRepository
) {

    private var language: String = Locale.getDefault().language


    suspend fun fetchAchievements(
        onSuccess: (List<Achievement>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        val collectionReference = firestore.collection("achievements")

        try{
            val achievements = withContext(Dispatchers.IO) {
                val documents = collectionReference.get().await()

                documents.map{data ->
                    val achievementResponse = data.toObject<AchievementResponse>()

                    val imageRef = storage.reference.child("achievements/${achievementResponse.image}.png")
                    val imageUrl = imageRef.downloadUrl.await() // Use await after launching coroutine

                    Achievement(

                        id = data.id,
                        imageUri = imageUrl,
                        name = achievementResponse.name ,
                        progress = achievementResponse.progress ,
                        description =
                        if (language == Language.ID.code) achievementResponse.description?.id
                        else achievementResponse.description?.en ,
                        category = achievementResponse.category
                    )
                }
            }
            onSuccess(achievements)
        } catch(e: Exception){
            onFailure(e)
        }
    }

    fun fetchAchievementsByCategoryWithoutImage(
        categoryString: String,
        onSuccess: (List<Achievement>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){

        val collectionReference = firestore.collection("achievements")

        collectionReference
            .whereEqualTo("category", categoryString)
            .get()
            .addOnSuccessListener {documents ->

                val achievements = documents.map{data ->
                    val achievementResponse = data.toObject<AchievementResponse>()

                    Achievement(
                        imagePath = achievementResponse.image,
                        name = achievementResponse.name ,
                        progress = achievementResponse.progress ,
                        description =
                        if (language == Language.ID.code) achievementResponse.description?.id
                        else achievementResponse.description?.en ,
                        category = achievementResponse.category
                    )
                }
                onSuccess(achievements)
            }
            .addOnFailureListener(onFailure)
    }

    suspend fun fetchAchievementById(
        achievementId: String,
        onSuccess: (Achievement) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        Log.d(TAG, "AchievementId: ${achievementId}")

        try{
            val achievement = withContext(Dispatchers.IO) {
                val document =  firestore.collection("achievements").document(achievementId).get().await()

                val achievementResponse = document.toObject<AchievementResponse>()!!

                Log.d(TAG, "AchievementResponse Image: ${achievementResponse.image}")

                val imageRef = storage.reference.child("achievements/${achievementResponse.image}.png")

                val imageUrl = imageRef.downloadUrl.await() // Use await after launching coroutine

                Achievement(
                    imageUri = imageUrl,
                    name = achievementResponse.name ,
                    progress = achievementResponse.progress ,
                    description =
                    if (language == Language.ID.code) achievementResponse.description?.id
                    else achievementResponse.description?.en ,
                    category = achievementResponse.category
                )

            }

            onSuccess(achievement)
        } catch(e: Exception){
            Log.e(TAG, "Error: ${e}")
            onFailure(e)
        }
    }

    suspend fun getAchievementIdByProgressCondition(
        progress: Int,
        categoryString: String,
    ): String{
        val collectionReference = firestore.collection("achievements")
        val query = collectionReference
            .whereEqualTo("category", categoryString)
            .whereEqualTo("progress", progress)
            .get()
            .await()

        val achievementId = query.first().id

        Log.d(TAG, "Achievement Id: $achievementId")
        return achievementId
    }

//    suspend fun fetchTodayChallenges(
//        personalizationCategory: Category? = null,
//        onSuccess: (List<Challenge>) -> Unit,
//        onFailure: (Throwable) -> Unit
//    ){
//
//        val todayDate = Date()
//        val accountCreatedDate = userRepository.getAccountCreatedDate()!!
//
////        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L
//        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L
//
//        Log.d(TAG, "Is personalization day value = $isPersonalizationDay")
//
//        val collectionReference = firestore.collection("challenges")
//
//        ////
//
//        val todayChallenges: MutableList<Challenge> = mutableListOf()
//
//        if(isPersonalizationDay){
//            val personalizationChallengeDocument = collectionReference
//                .whereEqualTo("challengeType", "Personalization")
//                .get()
//                .await()
//                .firstOrNull()
//
//            val personalizationChallengeResponse = personalizationChallengeDocument?.toObject<ChallengeResponse>()
//
//            personalizationChallengeResponse?.let{ todayChallenges.add(personalizationChallengeResponse.toChallenge(personalizationChallengeDocument.id)) }
//        }
//        if(personalizationCategory != null){
//
//            val personalizationCategoryString = personalizationCategory.stringValue
//
//            val personalizedChallengeDocument = collectionReference
//                .whereEqualTo("challengeType", "Practice")
//                .whereEqualTo("selfCareCategory", personalizationCategoryString)
//                .get()
//                .await()
//                .firstOrNull()
//
//            val personalizedChallengeResponse = personalizedChallengeDocument?.toObject<ChallengeResponse>()
//
//            personalizedChallengeResponse?.let{ todayChallenges.add(personalizedChallengeResponse.toChallenge(personalizedChallengeDocument.id)) }
//
//            val challengeDocument =  collectionReference
//                .whereEqualTo("challengeType", "Practice")
//                .whereNotEqualTo("selfCareCategory", personalizationCategoryString)
//                .get()
//                .await()
//
//            challengeDocument.map {data ->
//                val challengeResponse = data.toObject<ChallengeResponse>()
//
//                challengeResponse.toChallenge(data.id)
//            }.shuffled().forEach{challenge ->
//                todayChallenges.add(challenge)
//            }
//
//        } else {
//
//            val challengeDocument =  collectionReference
//                .whereEqualTo("challengeType", "Practice")
//                .get()
//                .await()
//
//            challengeDocument.map {data ->
//                val challengeResponse = data.toObject<ChallengeResponse>()
//
//                challengeResponse.toChallenge(data.id)
//            }.shuffled().forEach{challenge ->
//                todayChallenges.add(challenge)
//            }
//
//        }
//
//        onSuccess(todayChallenges.subList(0, 3))
//
////        try{
////
////        } catch(e: Exception){
////            onFailure(e)
////        }
//    }



    suspend fun getTodayChallenge(

        onSuccess: (List<Challenge>) -> Unit,
        onFailure: (Throwable) -> Unit
    ){
        val currentUser = userRepository.getCurrentUser()!!
        val userId = currentUser.uid
        val date = Calendar.getInstance().time

        val todayChallenge = generateTodayChallenge()
        val dateString = DateUtils.getYearMonthDayFormat(date)

        val docRef = firestore.collection("users").document(userId).collection("today_challenges")
            .document(dateString)

        docRef.get().addOnSuccessListener {
            val todayChallengeResponse = it.toObject<TodayChallengeResponse>()!!

            val todayChallengeItems = todayChallengeResponse.challenges

            val todayChallengeList = todayChallengeItems!!.map { challengeItem ->
                val selfCareCategory = Category.fromName(challengeItem?.selfCareCategory ?: "Emotional")
                Challenge(
                    id = challengeItem?.id!!,
                    title = challengeItem?.title ?: "Untitled Challenge",
                    description = challengeItem?.description ?: "No description available",
                    challengeType = ChallengeType.fromString(challengeItem?.challengeType ?: "Practice", selfCareCategory.stringValue),
                    selfCareCategory = selfCareCategory,
                    progress = challengeItem?.progress ?: 0,
                    isDone = challengeItem?.isDone ?: false

                )
            }

            onSuccess(todayChallengeList)

        }.addOnFailureListener {
            onFailure(it)
        }
    }

    suspend fun addTodayChallenge(personalizationCategory: Category?){
        val currentUser = userRepository.getCurrentUser()!!
        val userId = currentUser.uid
        val date = Calendar.getInstance().time

        val todayChallenge = generateTodayChallenge(personalizationCategory)
        val dateString = DateUtils.getYearMonthDayFormat(date)

        val map: Map<String, Any> = mapOf(
            "date_generated" to date.time,
            "challenges" to todayChallenge.map{ it.toMap() },
        )

        val docRef = firestore.collection("users").document(userId).collection("today_challenges")
                .document(dateString)

        docRef.get().addOnSuccessListener{ snapshot ->
            if (!snapshot.exists()) {
                docRef.set(map)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.e(TAG, "Error writing document", e) }
            } else {
                Log.d(TAG, "Document with $dateString already exists")
            }
        }.addOnFailureListener { Log.w(TAG, "Error fetching document:", it) }

    }

    private suspend fun generateTodayChallenge(
        personalizationCategory: Category? = null,
    ): List<Challenge> {

        val todayDate = Date()
        val accountCreatedDate = userRepository.getAccountCreatedDate()!!

//        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L
        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L

        Log.d(TAG, "Is personalization day value = $isPersonalizationDay ${DateUtils.daysBetween(todayDate, accountCreatedDate)}")

        val collectionReference = firestore.collection("challenges")

        ////

        val todayChallenges: MutableList<Challenge> = mutableListOf()

        if(isPersonalizationDay){
            val personalizationChallengeDocument = collectionReference
                .whereEqualTo("challengeType", "Personalization")
                .get()
                .await()
                .firstOrNull()

            val personalizationChallengeResponse = personalizationChallengeDocument?.toObject<ChallengeResponse>()

            personalizationChallengeResponse?.let{ todayChallenges.add(personalizationChallengeResponse.toChallenge(personalizationChallengeDocument.id)) }
        }
        if(personalizationCategory != null){

            val personalizationCategoryString = personalizationCategory.stringValue

            val personalizedChallengeDocument = collectionReference
                .whereEqualTo("challengeType", "Practice")
                .whereEqualTo("selfCareCategory", personalizationCategoryString)
                .get()
                .await()
                .firstOrNull()

            val personalizedChallengeResponse = personalizedChallengeDocument?.toObject<ChallengeResponse>()

            personalizedChallengeResponse?.let{ todayChallenges.add(personalizedChallengeResponse.toChallenge(personalizedChallengeDocument.id)) }

            val challengeDocument =  collectionReference
                .whereEqualTo("challengeType", "Practice")
                .whereNotEqualTo("selfCareCategory", personalizationCategoryString)
                .get()
                .await()

            val random = Random(DateUtils.getDayOfMonth(todayDate).toLong())

            challengeDocument.map {data ->
                val challengeResponse = data.toObject<ChallengeResponse>()

                challengeResponse.toChallenge(data.id)
            }.shuffled(random).forEach{challenge ->
                todayChallenges.add(challenge)
            }

        } else {

            val challengeDocument =  collectionReference
                .whereEqualTo("challengeType", "Practice")
                .get()
                .await()

            val random = Random(DateUtils.getDayOfMonth(todayDate).toLong())

            challengeDocument.map {data ->
                val challengeResponse = data.toObject<ChallengeResponse>()

                challengeResponse.toChallenge(data.id)

            }.shuffled(random).forEach{challenge ->
                todayChallenges.add(challenge)
            }

        }

        return todayChallenges.subList(0, 3)
    }
    companion object{
        const val TAG = "GamificationRepository"
    }
}