package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.utils.Language
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamificationRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore,
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
        progress: Int
    ): String{

        return ""
    }

    companion object{
        const val TAG = "GamificationRepository"
    }
}