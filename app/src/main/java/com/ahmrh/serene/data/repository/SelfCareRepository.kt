package com.ahmrh.serene.data.repository

import android.net.Uri
import android.util.Log
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.source.remote.response.ActivityResponse
import com.ahmrh.serene.domain.model.SelfCareActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelfCareRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
) {
    fun fetchSupportingImageUri(supportingImage: String): Flow<ResourceState<Uri>> = callbackFlow {

        val storageReference = storage.reference

        val imageRef = storageReference.child(
            "activities/${supportingImage}.png"
        )

        imageRef.downloadUrl.addOnSuccessListener {
            trySend(
                ResourceState.Success(it)
            )
        }.addOnFailureListener{ exception ->
            trySend(
                ResourceState.Failed(
                    exception
                )
            )
        }

        awaitClose {
            close()
        }
    }

    // TODO: Make the image work somehow
    fun fetchActivities(
        category: Category
    ): Flow<ResourceState<List<SelfCareActivity>>> =
        callbackFlow {

            val collectionReference =
                db.collection("activities")

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

                                SelfCareActivity(
                                    id = data.id,
                                    imageUri = imageUri,
                                    name = activityResponse.name,
                                    description = activityResponse.description,
                                    category = activityResponse.category,
                                    guide = activityResponse.guide,
                                    benefit = activityResponse.benefit
                                )
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

//    fun fetchActivities(): Flow<ResourceState<List<SelfCareActivity>>> =
//        callbackFlow {
//
//
//            val collectionReference =
//                db.collection("activities")
//
//            collectionReference.get()
//                .addOnSuccessListener { document ->
//                    val activities =
//                        document.map { data ->
//
//                            val activityResponse =
//                                data.toObject<ActivityResponse>()
//
//                            SelfCareActivity(
//                                id = data.id,
////                                supportingImageUri = activityResponse.supportingImage,
//                                name = activityResponse.name,
//                                description = activityResponse.description,
//                                category = activityResponse.category,
//                                guide = activityResponse.guide,
//                                benefit = activityResponse.benefit
//                            )
//
//                        }
//
//                    trySend(
//                        ResourceState.Success(
//                            activities
//                        )
//                    )
//
//                }
//                .addOnFailureListener { exception ->
//                    trySend(
//                        ResourceState.Failed(
//                            exception
//                        )
//                    )
//                }
//
//            awaitClose {
//                close()
//            }
//        }

    fun fetchActivity(
        id: String
    ): Flow<ResourceState<SelfCareActivity>> =
        callbackFlow {

            val storageReference = storage.reference

            val collectionReference =
                db.collection("activities")

            collectionReference.document(id).get()
                .addOnSuccessListener {data ->
                    async{

                        val activityResponse =
                            data.toObject<ActivityResponse>()

                        val imageRef = storageReference.child(
                            "activities/${activityResponse?.image}.png"
                        )
                        val imageUri = imageRef.downloadUrl.await()

                        val activity = SelfCareActivity(
                            id = data.id,
                            imageUri = imageUri,
                            name = activityResponse?.name,
                            description = activityResponse?.description,
                            category = activityResponse?.category,
                            guide = activityResponse?.guide,
                            benefit = activityResponse?.benefit
                        )

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