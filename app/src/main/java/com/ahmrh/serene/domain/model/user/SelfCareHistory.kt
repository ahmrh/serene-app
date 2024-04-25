package com.ahmrh.serene.domain.model.user

import java.util.Date

data class SelfCareHistory(
    val selfCareId: String,
    val selfCareCategory: String,
    val feedbackSentiment: String,
    val selfCareName: String,
    val date: Date,

)
fun SelfCareHistory.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["selfCareId"] = selfCareId
    map["feedbackSentiment"] = feedbackSentiment
    map["selfCareCategory"] = selfCareCategory
    map["selfCareName"] = selfCareName
    map["date"] = date.time // Store date as milliseconds since epoch

    return map.toMap()
}