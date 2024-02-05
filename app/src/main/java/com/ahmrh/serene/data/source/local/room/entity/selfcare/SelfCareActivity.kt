package com.ahmrh.serene.data.source.local.room.entity.selfcare

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "selfcare_activity",
    foreignKeys = [
        ForeignKey(
            entity = SelfCareCategory::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        )
    ]
)
data class SelfCareActivity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(
        name = "description"
    ) val description: String,
    @ColumnInfo(
        name = "category_id"
    ) val categoryId: Long,
    @ColumnInfo(
        name = "guide"
    ) val guide: String,
    @ColumnInfo(
        name = "benefit"
    ) val benefit: String,
)
