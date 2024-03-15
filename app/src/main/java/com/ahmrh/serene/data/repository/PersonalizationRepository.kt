package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.Language
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.source.remote.response.QuestionResponse
import com.ahmrh.serene.domain.model.PersonalizationQuestion
import com.ahmrh.serene.domain.model.PersonalizationResult
import com.ahmrh.serene.domain.model.toMap
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalizationRepository @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
) {

    private var language: String = Locale.getDefault().language
    companion object{
        const val TAG = "PersonalizationRepository"
    }
    init {
        Log.d(TAG, language)
    }

    fun fetchQuestions(
        listCategory: List<Category>
    ): Flow<ResourceState<List<PersonalizationQuestion>>> =
        callbackFlow {

            val collectionReference =
                firestore.collection("questions")

            val listCategoryString = listCategory.map{ category ->
                category.stringValue
            }

            collectionReference
                .whereIn("category", listCategoryString)
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val questions: List<PersonalizationQuestion> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<QuestionResponse>()

                                PersonalizationQuestion(
                                    questionString = if(language == Language.ID.code){
                                        "${activityResponse.questionString?.id}"
                                    } else {
                                        "${activityResponse.questionString?.en}"
                                    },
                                    category = activityResponse.category
                                )
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

    fun fetchQuestions(): Flow<ResourceState<List<PersonalizationQuestion>>> =
        callbackFlow {

            val collectionReference =
                firestore.collection("activities")


            collectionReference
                .get()
                .addOnSuccessListener { document ->
                    async {
                        val questions: List<PersonalizationQuestion> =
                            document.map { data ->
                                val activityResponse =
                                    data.toObject<QuestionResponse>()

                                PersonalizationQuestion(
                                    questionString = if(language == Language.ID.code){
                                        "${activityResponse.questionString?.id}"
                                    } else {
                                        "${activityResponse.questionString?.en}"
                                    },
                                    category = activityResponse.category
                                )
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

    fun savePersonalizationResult(
        category: Category
    ){
        val userId = Firebase.auth.currentUser!!.uid
        val date = Calendar.getInstance().time;


        val personalizationResult = PersonalizationResult(
            userId,
            category.stringValue,
            date
        )
        val result = personalizationResult.toMap()

        firestore.collection("personalization_results").document()
            .set(result)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }


    }

}