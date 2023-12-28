package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.Achievement
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.AchievementHistory

@Dao
interface AchievementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(achievement: Achievement)

    @Delete
    suspend fun delete(achievement: Achievement)

    @Query("DELETE FROM selfcare WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertHistory(achievementHistory: AchievementHistory)

}