package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.Language
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.source.remote.response.ActivityResponse
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelfCareRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
) {
    private var language: String = Locale.getDefault().language

    fun fetchActivities(
        category: Category
    ): Flow<ResourceState<List<SelfCareActivity>>> =
        callbackFlow {

            val collectionReference =
                firestore.collection("activities")

            val storageReference = storage.reference

            collectionReference
                .whereEqualTo("category", category.stringValue)
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val activities: List<SelfCareActivity> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<ActivityResponse>()

                                val imageRef = storageReference.child(
                                    "activities/${activityResponse.image}.png"
                                )
                                val imageUri = imageRef.downloadUrl.await()

                                if(language == Language.ID.code){

                                    SelfCareActivity(
                                        id = data.id,
                                        imageUri = imageUri,
                                        name = activityResponse.name?.id,
                                        description = activityResponse.description?.id,
                                        category = activityResponse.category,
                                        guide = activityResponse.guide?.id,
                                        benefit = activityResponse.benefit?.id
                                    )
                                } else {

                                    SelfCareActivity(
                                        id = data.id,
                                        imageUri = imageUri,
                                        name = activityResponse.name?.en,
                                        description = activityResponse.description?.en,
                                        category = activityResponse.category,
                                        guide = activityResponse.guide?.en,
                                        benefit = activityResponse.benefit?.en
                                    )
                                }

                            }

                        trySend(
                            ResourceState.Success(
                                activities
                            )
                        )
                    }


                }
                .addOnFailureListener { exception ->
                    trySend(
                        ResourceState.Failed(
                            exception
                        )
                    )
                    Log.d(TAG, "Error occurred: $exception")
                }

            awaitClose {
                close()
            }
        }

    fun fetchActivities(
        listCategory: List<Category>,
    ): Flow<ResourceState<List<SelfCareActivity>>> =
        callbackFlow {


            val collectionReference =
                firestore.collection("activities")

            val storageReference = storage.reference

            val listCategoryString = listCategory.map{ category ->
                category.stringValue
            }

            collectionReference
                .whereIn("category", listCategoryString)
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val activities: List<SelfCareActivity> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<ActivityResponse>()

                                val imageRef = storageReference.child(
                                    "activities/${activityResponse.image}.png"
                                )
                                val imageUri = imageRef.downloadUrl.await()

                                if(language == Language.ID.code){
                                    SelfCareActivity(
                                        id = data.id,
                                        imageUri = imageUri,
                                        name = activityResponse.name?.id,
                                        description = activityResponse.description?.id,
                                        category = activityResponse.category,
                                        guide = activityResponse.guide?.id,
                                        benefit = activityResponse.benefit?.id
                                    )
                                } else {
                                    SelfCareActivity(
                                        id = data.id,
                                        imageUri = imageUri,
                                        name = activityResponse.name?.en,
                                        description = activityResponse.description?.en,
                                        category = activityResponse.category,
                                        guide = activityResponse.guide?.en,
                                        benefit = activityResponse.benefit?.en
                                    )
                                }
                            }

                        trySend(
                            ResourceState.Success(
                                activities
                            )
                        )
                    }


                }
                .addOnFailureListener { exception ->
                    trySend(
                        ResourceState.Failed(
                            exception
                        )
                    )
                    Log.d(TAG, "Error occurred: $exception")
                }

            awaitClose {
                close()
            }
        }


    fun fetchActivity(
        id: String
    ): Flow<ResourceState<SelfCareActivity>> =
        callbackFlow {

            val storageReference = storage.reference

            val collectionReference =
                firestore.collection("activities")

            collectionReference.document(id).get()
                .addOnSuccessListener {data ->
                    async{

                        val activityResponse =
                            data.toObject<ActivityResponse>()

                        val imageRef = storageReference.child(
                            "activities/${activityResponse?.image}.png"
                        )
                        val imageUri = imageRef.downloadUrl.await()

                        val activity: SelfCareActivity
                        if(language == Language.ID.code){

                            activity = SelfCareActivity(
                                id = data.id,
                                imageUri = imageUri,
                                name = activityResponse?.name?.id,
                                description = activityResponse?.description?.id,
                                category = activityResponse?.category,
                                guide = activityResponse?.guide?.id,
                                benefit = activityResponse?.benefit?.id
                            )
                        } else {

                            activity = SelfCareActivity(
                                id = data.id,
                                imageUri = imageUri,
                                name = activityResponse?.name?.en,
                                description = activityResponse?.description?.en,
                                category = activityResponse?.category,
                                guide = activityResponse?.guide?.en,
                                benefit = activityResponse?.benefit?.en
                            )
                        }

                        trySend(
                            ResourceState.Success(activity)
                        )
                    }

                }
                .addOnFailureListener{
                    trySend(
                        ResourceState.Failed(it)
                    )
                }

            awaitClose{
                close()
            }
        }



    companion object {
        const val TAG = "SelfCareRepository";
    }

}