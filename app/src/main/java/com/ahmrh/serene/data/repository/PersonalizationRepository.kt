package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.source.remote.response.ActivityResponse
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalizationRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
) {
    companion object{
        const val TAG = "PersonalizationRepository"
    }

    fun fetchQuestions(
        listCategory: List<Category>
    ): Flow<ResourceState<List<String>>> =
        callbackFlow {

            val collectionReference =
                firestore.collection("activities")

            val listCategoryString = listCategory.map{ category ->
                category.stringValue
            }

            collectionReference
                .whereIn("category", listCategoryString)
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val questions: List<String> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<ActivityResponse>()

                                ""
                            }

                        trySend(
                            ResourceState.Success(
                                questions
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
                    Log.d(SelfCareRepository.TAG, "Error occurred: $exception")
                }

            awaitClose {
                close()
            }
        }

    fun fetchQuestions(): Flow<ResourceState<List<String>>> =
        callbackFlow {

            val collectionReference =
                firestore.collection("activities")


            collectionReference
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val questions: List<String> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<ActivityResponse>()

                                activityResponse.name.toString()
                            }

                        trySend(
                            ResourceState.Success(
                                questions
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
                    Log.d(SelfCareRepository.TAG, "Error occurred: $exception")
                }

            awaitClose {
                close()
            }
        }



}