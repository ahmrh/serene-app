package com.ahmrh.serene.domain.model.gamification

import com.ahmrh.serene.domain.model.personalization.PersonalizationPoint
import java.util.Date

data class DailyStreak(
    val count: Int,
    val date: Date // ga kepake
)
fun DailyStreak.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["count"] = count
    map["date"] = date
    return map.toMap()
}

