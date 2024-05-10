package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.enums.Language
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.data.source.remote.response.ChallengeResponse
import com.ahmrh.serene.data.source.remote.response.toChallenge
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.ahmrh.serene.domain.model.gamification.toChallengeResponse
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

        try {
            val achievements = withContext(Dispatchers.IO) {
                val documents = collectionReference.get().await()

                documents.map { data ->
                    val achievementResponse =
                        data.toObject<AchievementResponse>()

                    val imageRef = storage.reference.child(
                        "achievements/${achievementResponse.image}.png"
                    )
                    val imageUrl =
                        imageRef.downloadUrl.await() // Use await after launching coroutine

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
            }
            onSuccess(achievements)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    fun fetchAchievementsByCategoryWithoutImage(
        categoryString: String,
        onSuccess: (List<Achievement>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {

        val collectionReference = firestore.collection("achievements")

        collectionReference
            .whereEqualTo("category", categoryString)
            .get()
            .addOnSuccessListener { documents ->

                val achievements = documents.map { data ->
                    val achievementResponse =
                        data.toObject<AchievementResponse>()

                    Achievement(
                        imagePath = achievementResponse.image,
                        name = achievementResponse.name,
                        progress = achievementResponse.progress,
                        description =
                        if (language == Language.ID.code) achievementResponse.description?.id
                        else achievementResponse.description?.en,
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

        try {
            val achievement = withContext(Dispatchers.IO) {
                val document = firestore.collection("achievements")
                    .document(achievementId).get().await()

                val achievementResponse =
                    document.toObject<AchievementResponse>()!!

                Log.d(
                    TAG,
                    "AchievementResponse Image: ${achievementResponse.image}"
                )

                val imageRef = storage.reference.child(
                    "achievements/${achievementResponse.image}.png"
                )

                val imageUrl =
                    imageRef.downloadUrl.await() // Use await after launching coroutine

                Achievement(
                    imageUri = imageUrl,
                    name = achievementResponse.name,
                    progress = achievementResponse.progress,
                    description =
                    if (language == Language.ID.code) achievementResponse.description?.id
                    else achievementResponse.description?.en,
                    category = achievementResponse.category
                )

            }

            onSuccess(achievement)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e}")
            onFailure(e)
        }
    }

    suspend fun getAchievementIdByProgressCondition(
        progress: Int,
        categoryString: String,
    ): String {
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

    suspend fun addChallengeProgress(
        challengeType: ChallengeType
    ) {
        val currentUser = userRepository.getCurrentUser()!!
        val userId = currentUser.uid
        val dateString =
            DateUtils.getYearMonthDayFormat(Calendar.getInstance().time)

        val docRef = firestore.collection("users").document(userId)
            .collection("today_challenges")
            .document(dateString).collection("challenges")

        val todayChallenges =
            docRef.get().await().documents.mapNotNull { data ->
                val challengeResponse =
                    data.toObject<ChallengeResponse>()

                challengeResponse?.toChallenge(data.id)
            }


        when (challengeType) {
            is ChallengeType.PRACTICE -> {
                val categoryString = challengeType.category.stringValue

                val challenge =
                    todayChallenges.first {
                        it.selfCareCategory.stringValue == categoryString
                    }
                val data = hashMapOf("done" to true)

                docRef.document(challenge.id).update(
                    data as Map<String, Any>
                )
                    .addOnSuccessListener {
                        Log.d(
                            TAG, "DocumentSnapshot successfully updated!"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            TAG, "Error updating document", e
                        )
                    }
            }

            is ChallengeType.PERSONALIZATION -> {

                val challenge =
                    todayChallenges.firstOrNull {
                        it.challengeType is ChallengeType.PERSONALIZATION
                    } ?: return

                val data = hashMapOf("done" to true)

                docRef.document(challenge.id).update(
                    data as Map<String, Any>
                )
                    .addOnSuccessListener {
                        Log.d(
                            TAG, "DocumentSnapshot successfully updated!"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            TAG, "Error updating document", e
                        )
                    }

            }

            else -> {}
        }
    }


    suspend fun fetchTodayChallenge(
        personalizationCategory: Category?,
        onSuccess: (List<Challenge>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        val todayChallenge =
            generateTodayChallenge(personalizationCategory)
        addTodayChallenge(todayChallenge)


        val currentUser = userRepository.getCurrentUser()!!

        val userId = currentUser.uid
        val date = Calendar.getInstance().time


        val dateString = DateUtils.getYearMonthDayFormat(date)

        val docRef = firestore.collection("users").document(userId)
            .collection("today_challenges")
            .document(dateString).collection("challenges")

        docRef
            .get()
            .addOnSuccessListener {

                val challengeList = it.documents.map { data ->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()

                    val isDone = data.get("done") as Boolean

                    val challenge = challengeResponse?.toChallenge(data.id)

                    Challenge(
                        id = data.id,
                        title = challenge?.title ?: "Unidentified Title",
                        description = challenge?.description ?: "There is no description",
                        challengeType = ChallengeType.fromString(challenge?.challengeType?.stringValue ?: "Personalization", challenge?.selfCareCategory?.stringValue),
                        progress = challenge?.progress ?: 0,
                        selfCareCategory = Category.fromName(challenge?.selfCareCategory?.stringValue ?: "Emotional"),
                        isDone = isDone
                    )
                }


                onSuccess(challengeList)

            }
            .addOnFailureListener(onFailure)


    }


    private fun addTodayChallenge(
        todayChallenge: List<Challenge>
    ) {
        val currentUser = userRepository.getCurrentUser()!!

        val userId = currentUser.uid
        val date = Calendar.getInstance().time

        val dateString = DateUtils.getYearMonthDayFormat(date)
        Log.d(TAG, "Date String: $dateString")

        val docRef = firestore.collection("users").document(userId)
            .collection("today_challenges")
            .document(dateString)


        docRef.get().addOnSuccessListener { snapshot ->
            Log.d(TAG, "Snapshot Exists: ${snapshot.exists()}")

            if (!snapshot.exists()) {
                docRef
                    .set(mapOf("date" to date))
                    .addOnSuccessListener {
                        Log.d(
                            TAG, "DocumentSnapshot successfully written!"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error writing document", e)
                    }

                todayChallenge.forEach { challenge ->
                    docRef
                        .collection("challenges")
                        .document(challenge.id)
                        .set(challenge.toChallengeResponse())
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully written!"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error writing document", e)
                        }
                }
            } else {
                Log.d(TAG, "Document with $dateString already exists")
            }

        }.addOnFailureListener {
            Log.w(
                TAG, "Error fetching document:", it
            )
        }


    }


    private suspend fun generateTodayChallenge(
        personalizationCategory: Category?,
    ): List<Challenge> {

        val todayDate = Date()
        val accountCreatedDate = userRepository.getAccountCreatedDate()!!

        val isPersonalizationDay = (DateUtils.daysBetween(
            todayDate, accountCreatedDate
        ) % 7L == 0L) && !DateUtils.isSameDay(
            todayDate, accountCreatedDate
        )

        val random = Random(DateUtils.getDayOfMonth(todayDate).toLong())

        Log.d(
            TAG,
            "Logging value when adding challenge \npersonalizationDay: $isPersonalizationDay \ndaysBetween: ${
                DateUtils.daysBetween(todayDate, accountCreatedDate)
            } \npersonalizationCategory: $personalizationCategory \nrandom: $random"
        )


        val collectionReference = firestore.collection("challenges")


        val todayChallenges: MutableList<Challenge> = mutableListOf()

        if (isPersonalizationDay) {
            val personalizationChallengeDocument = collectionReference
                .whereEqualTo("challengeType", "Personalization")
                .get()
                .await()
                .firstOrNull()

            val personalizationChallengeResponse =
                personalizationChallengeDocument?.toObject<ChallengeResponse>()

            personalizationChallengeResponse?.let {
                todayChallenges.add(
                    personalizationChallengeResponse.toChallenge(
                        personalizationChallengeDocument.id
                    )
                )
            }
        }

        val challengeDocument = collectionReference
            .whereEqualTo("challengeType", "Practice")
            .get()
            .await()

        personalizationCategory?.let { category ->
            challengeDocument.documents
                .filter { data ->
                    data.getString(
                        "selfCareCategory"
                    ) == category.stringValue
                }
                .map { data ->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()

                    challengeResponse?.toChallenge(data.id)
                }
        }.let { challengeList ->
            challengeList?.first().let { challenge ->
                if (challenge != null)
                    todayChallenges.add(challenge)
            }

        }

        personalizationCategory.let { category ->
            challengeDocument.documents
                .filter { data ->
                    if (category != null) {
                        data.getString(
                            "selfCareCategory"
                        ) != category.stringValue
                    } else true
                }
                .map { data ->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()!!

                    challengeResponse.toChallenge(data.id)
                }
        }.shuffled(random).forEach {
            todayChallenges.add(it)

        }


        return todayChallenges.subList(0, 3)
    }

    companion object {
        const val TAG = "GamificationRepository"
    }
}