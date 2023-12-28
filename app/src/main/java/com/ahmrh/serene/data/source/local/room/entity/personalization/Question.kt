package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "answer_json") val answerJson: String,
)
