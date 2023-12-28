package com.ahmrh.serene.data.source.local.room.entity.gamification.achievement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievement")
data class Achievement(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_uri") val imageUri: String,

    /* TODO */
    // need some work on how the condition work
)
