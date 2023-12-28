package com.ahmrh.serene.data.source.local.room.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(

    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "joined_at") val joinedAt: String,
    @ColumnInfo(name = "last_accessed") val lastAccessed: String,
)
