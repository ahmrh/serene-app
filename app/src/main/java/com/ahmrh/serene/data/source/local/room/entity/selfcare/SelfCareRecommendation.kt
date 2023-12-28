package com.ahmrh.serene.data.source.local.room.entity.selfcare

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selfcare_recommendation")
data class SelfCareRecommendation(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "selfcare_id") val selfCareId: Long,
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,

)