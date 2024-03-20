package com.ahmrh.serene.data.repository

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
    private val firestore: FirebaseFirestore
) {

    private var language: String = Locale.getDefault().language

    suspend fun fetchAchievements(
        onSuccess: (List<Achievement>) -> Unit,
        onFailed: (Throwable?) -> Unit
    ) {
        val collectionReference = firestore.collection("achievements")

        try{
            val achievements = withContext(Dispatchers.IO) {
                val documents = collectionReference.get().await()

                documents.map{data ->
                    val achievementResponse = data.toObject<AchievementResponse>()

                    val imageRef = storage.reference.child("achievement/${achievementResponse.image}.png")
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
            }
            onSuccess(achievements)
        } catch(e: Exception){
            onFailed(e)
        }
    }

    suspend fun fetchAchievement(
        achievementId: String,
        onSuccess: (Achievement) -> Unit,
        onFailed: (Throwable?) -> Unit
    ) {

        val collectionReference = firestore.collection("achievements")

        try{
            val achievement = withContext(Dispatchers.IO) {
                val document = collectionReference.document(achievementId).get().await()

                val achievementResponse = document.toObject<AchievementResponse>()!!
                val imageRef = storage.reference.child("achievement/${achievementResponse.image}.png")

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
            onFailed(e)
        }
    }

    suspend fun fetchUserAchievement(
        userId: String,
        onSuccess: (List<Achievement>) -> Unit,
        onFailed: (Throwable?) -> Unit
    ){

        val collectionReference = firestore.collection("users")

        try{
            val achievements = withContext(Dispatchers.IO) {
                val documents = collectionReference.document(userId).collection("achievements").get().await()

                val achievements = documents.map{ data ->
                    val achievementResponse = data.toObject<AchievementResponse>()

                    val imageRef = storage.reference.child("achievement/")

                }
                val imageRef = storage.reference.child("achievement/${achievementResponse.image}.png")

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
            onFailed(e)
        }
    }
}