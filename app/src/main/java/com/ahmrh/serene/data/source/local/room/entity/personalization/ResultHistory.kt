package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmrh.serene.domain.model.PersonalizationPoint

@Entity(tableName = "result_history")
data class ResultHistory(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "point") val point: PersonalizationPoint, // point for each selfcare category
    @ColumnInfo(
        name = "created_at",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createdAt: String,
)

//point json
//{
//    "1": 4,
//    "2": 5,
//    "3": 10,
//    "4": 4,
//    "5": 5,
//    "6": 10,
//    "7": 10,
//}
