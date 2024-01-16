package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import com.ahmrh.serene.data.source.local.room.entity.personalization.ResultHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalizationDao {

    // Question
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)
    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT * from question ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<Question>>

    @Query("SELECT * from question WHERE id = :id")
    fun getQuestionById(id: Int): Flow<Question>

    @Query("DELETE FROM question WHERE id = :id")
    suspend fun deleteQuestionById(id: Long)


    // Result
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: ResultHistory)
    @Delete
    suspend fun deleteResult(result: ResultHistory)
    @Query("DELETE FROM result_history WHERE id = :id")
    suspend fun deleteResultById(id: Long)
}