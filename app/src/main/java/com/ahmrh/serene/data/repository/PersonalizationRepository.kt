package com.ahmrh.serene.data.repository

import com.ahmrh.serene.data.source.local.room.dao.PersonalizationDao
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersonalizationRepository(

    private val dao: PersonalizationDao,
) {
    init {
        println("PersonalizationRepository created")
    }

    fun fetchQuestion(){

    }


    fun getAllQuestionsStream(): Flow<List<Question>> = dao.getAllQuestions()

    val dummyQuestionsStream: Flow<List<Question>> =  flow {
        while(true){
            emit(listOf())
            delay(200)

        }
    }


    companion object {
        const val TAG = "Personalization Repository"
    }
}