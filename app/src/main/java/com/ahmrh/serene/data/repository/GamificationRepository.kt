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





    // TODO: Something wrong here
     suspend fun generateTodayChallenge(
        personalizationCategory: Category? ,
    ): List<Challenge> {

        val todayDate = Date()
        val accountCreatedDate = userRepository.getAccountCreatedDate()!!

//        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L
        val isPersonalizationDay =
            DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L

        val random = Random(DateUtils.getDayOfMonth(todayDate).toLong())

        Log.d(
            TAG, "Logging value when adding challenge \npersonalizationDay: $isPersonalizationDay \ndaysBetween: ${
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

        personalizationCategory?.let {category ->
            challengeDocument.documents
                .filter { data ->
                    data.getString(
                        "selfCareCategory"
                    ) == category.stringValue
                }
                .map { data->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()

                    challengeResponse?.toChallenge(data.id)
                }
        }.let{ challengeList ->
            challengeList?.first().let{ challenge ->
                if(challenge != null)
                    todayChallenges.add(challenge)
            }

        }

        personalizationCategory.let {category ->
            challengeDocument.documents
                .filter { data ->
                    if(category != null){
                        data.getString(
                            "selfCareCategory"
                        ) != category.stringValue
                    } else true
                }
                .map { data->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()!!

                    challengeResponse.toChallenge(data.id)
                }
        }.shuffled(random).forEach{
            todayChallenges.add(it)

        }


        return todayChallenges.subList(0, 3)
    }

    companion object {
        const val TAG = "GamificationRepository"
    }
}