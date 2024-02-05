package com.ahmrh.serene.data.source.local.room.entity.gamification.achievement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "achievement_history",
    foreignKeys = [
        ForeignKey(
            entity = Achievement::class,
            parentColumns = ["id"],
            childColumns = ["achievement_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class AchievementHistory(

    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(
        name = "achievement_id"
    ) val achievementId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)
