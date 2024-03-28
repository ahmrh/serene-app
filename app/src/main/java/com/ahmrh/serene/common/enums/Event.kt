package com.ahmrh.serene.common.enums

import com.ahmrh.serene.domain.model.gamification.DailyStreak

sealed class Event {
    data class DailyStreakEvent(val dailyStreak: DailyStreak) : Event()
    data class AchievementEvent(val achievementId: String): Event()
}
