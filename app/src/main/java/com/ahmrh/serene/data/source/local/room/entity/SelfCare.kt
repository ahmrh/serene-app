package com.ahmrh.serene.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selfcare")
data class SelfCare(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: Int,
    @ColumnInfo(name = "guide") val guide: String,
    @ColumnInfo(name = "benefit") val benefit: String,
)
