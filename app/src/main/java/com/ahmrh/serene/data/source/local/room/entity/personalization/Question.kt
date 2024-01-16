package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// TODO create something to parse answer_json
@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "answers") val answers: String,
    @ColumnInfo(name = "question_json") val questionJson: String,
)


//{
// lets try put question first inside the app and see whats happen
//}
