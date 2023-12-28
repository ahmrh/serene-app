package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.Achievement
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.AchievementHistory
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.Challenge
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.ChallengeHistory

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(challenge: Challenge)

    @Delete
    suspend fun delete(challenge: Challenge)

    @Query("DELETE FROM selfcare WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertHistory(challengeHistory: ChallengeHistory)
}