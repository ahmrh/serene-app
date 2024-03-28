package com.ahmrh.serene.domain.usecase.gamification

import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import javax.inject.Inject

class GetAchievementListUseCase @Inject constructor(
    private val gamificationRepository: GamificationRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        onSuccess: (List<Achievement>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) = userRepository.fetchUserAchievements(onSuccess, onFailure)
}