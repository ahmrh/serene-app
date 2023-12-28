package com.ahmrh.serene.data.source.local.room.entity.gamification.achievement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievement_history")
data class AchievementHistory(

    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "achievement_id") val achievementId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)
