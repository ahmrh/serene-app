package com.ahmrh.serene.data.source.local.room.entity.gamification.challenge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "challenge_history",
    foreignKeys = [
        ForeignKey(
            entity = Challenge::class,
            parentColumns = ["id"],
            childColumns = ["challenge_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class ChallengeHistory(

    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(
        name = "challenge_id"
    ) val challengeId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)
