package com.ahmrh.serene.data.source.local.room.entity.selfcare

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "selfcare_recommendation",
    foreignKeys = [
        ForeignKey(
            entity = SelfCareActivity::class,
            parentColumns = ["id"],
            childColumns = ["selfcare_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class SelfCareRecommendation(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(
        name = "selfcare_id"
    ) val selfCareId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,

    )