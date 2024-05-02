package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.enums.Language
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.data.source.remote.response.ChallengeResponse
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.Locale
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

    suspend fun fetchTodayChallenges(
        onSuccess: (List<Challenge>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){

        val todayDate = Date()
        val accountCreatedDate = userRepository.getAccountCreatedDate()!!

        val isPersonalizationDay = DateUtils.daysBetween(todayDate, accountCreatedDate) % 7L == 0L

        val collectionReference = firestore.collection("challenges")
        try{
            if(!isPersonalizationDay){

                val documents = collectionReference.whereEqualTo("Category", "").get().await()

                val challenges = documents.map { data ->
                    val challengeResponse =
                        data.toObject<ChallengeResponse>()

                    Challenge(
                        id = data.id,
                        title = challengeResponse.title
                            ?: "Unidentified Title",
                        description = challengeResponse.description
                            ?: "There is no description",
                        challengeType = ChallengeType.fromString(
                            challengeResponse.challengeType ?: "Practice",
                            challengeResponse.selfCareCategory
                        ),
                        progress = challengeResponse.progress ?: 0,
                        isDone = false
                    )
                }

                val todayChallenges = challenges.shuffled().take(3)

            }
            onSuccess(challenges)
        } catch(e: Exception){
            onFailure(e)
        }
    }

    companion object{
        const val TAG = "GamificationRepository"
    }
}