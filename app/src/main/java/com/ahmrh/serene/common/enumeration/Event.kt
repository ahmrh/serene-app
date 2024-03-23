package com.ahmrh.serene.common.enumeration

sealed class Event {
    data class DailyStreakEvent(val day: Int)
    data class AchievementEvent(val achievementId: String)
}