package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "point_json") val pointJson: String, // point for each data
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)
