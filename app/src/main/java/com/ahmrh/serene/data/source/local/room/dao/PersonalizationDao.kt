package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.Challenge
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.ChallengeHistory
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import com.ahmrh.serene.data.source.local.room.entity.personalization.Result

@Dao
interface PersonalizationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)
    @Delete
    suspend fun deleteQuestion(question: Question)


    @Query("DELETE FROM question WHERE id = :id")
    suspend fun deleteQuestionById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: Result)
    @Delete
    suspend fun deleteResult(result: Result)
    @Query("DELETE FROM result WHERE id = :id")
    suspend fun deleteResultById(id: Long)
}