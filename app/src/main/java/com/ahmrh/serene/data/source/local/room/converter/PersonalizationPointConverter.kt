package com.ahmrh.serene.data.source.local.room.converter

import androidx.room.TypeConverter
import com.ahmrh.serene.domain.model.PersonalizationPoint
import com.google.gson.Gson

class PersonalizationPointConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun pointToString(point: PersonalizationPoint): String{
        return gson.toJson(point)
    }

    @TypeConverter
    fun stringToPoint(string: String): PersonalizationPoint{
        return gson.fromJson(string, PersonalizationPoint::class.java)
    }
}