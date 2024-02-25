package com.ahmrh.serene.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersonalizationRepository(

) {
    init {
        println(
            "PersonalizationRepository created"
        )
    }


    fun fetchQuestion() {

    }

//    fun getAllQuestionsStream(): Flow<List<Question>> =
//        dao.getAllQuestions()
//
//    val dummyQuestionsStream: Flow<List<Question>> =
//        flow {
//            while (true) {
//                emit(listOf())
//                delay(200)
//            }
//        }

    companion object {
        const val TAG =
            "Personalization Repository"
    }
}