package com.ahmrh.serene.data.source.local.room.entity.gamification.challenge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_history")
data class ChallengeHistory(

    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "challenge_id") val challengeId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)
