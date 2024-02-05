package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareCategory

@Entity(
    tableName = "answer",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = ["id"],
            childColumns = ["question_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),
        ForeignKey(
            entity = SelfCareCategory::class,
            parentColumns = ["id"],
            childColumns =["category_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class Answer(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(
        name = "description"
    ) val description: String,
    @ColumnInfo(
        name = "question_id"
    ) val questionId: Long,
    @ColumnInfo(
        name = "category_id"
    ) val categoryId: Long,
)
