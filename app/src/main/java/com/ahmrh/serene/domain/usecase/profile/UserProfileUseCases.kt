package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.domain.usecase.gamification.GetAchievementUseCase
import javax.inject.Inject

data class UserProfileUseCases @Inject constructor(

    val getUsername: GetUsernameUseCase,
    val getProfileData: GetProfileDataUseCase,
    val getSelfCareHistory: GetSelfCareHistoryUseCase,
    val getUserStats: GetUserStatsUseCase,

    val updateAuthData: UpdateAuthDataUseCase,
    val getAuthData: GetAuthDataUseCase
)