package com.ahmrh.serene.domain.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PersonalizationResult (

    val userId: String,

    val category: String,


    val date: Date


)


fun PersonalizationResult.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["userId"] = userId
    map["category"] = category
    map["date"] = date.time // Store date as milliseconds since epoch

    return map.toMap()
}