package com.ahmrh.serene.data.source.local.room.entity.personalization

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer")
data class Answer(

    @PrimaryKey(autoGenerate = true) val id: Long,

    )
