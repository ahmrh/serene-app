package com.ahmrh.serene.domain.usecase.gamification

import javax.inject.Inject

data class GamificationUseCases @Inject constructor(
    val addAchievement: AddAchievementUseCase,
    val getAchievement: GetAchievementUseCase,
    val getAchievementList: GetAchievementListUseCase,
    val getTodayChallengeList: GetTodayChallengeListUseCase
)
