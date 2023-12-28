package com.ahmrh.serene.data.repository

import com.ahmrh.serene.data.source.local.room.dao.AchievementDao
import com.ahmrh.serene.data.source.local.room.dao.ChallengeDao

class GamificationRepository(
    private val achievementDao: AchievementDao,
    private val challengeDao: ChallengeDao,
) {
}