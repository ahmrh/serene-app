package com.ahmrh.serene.data.source.local.room.entity.selfcare

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selfcare_category")
data class SelfCareCategory(

    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,

)
