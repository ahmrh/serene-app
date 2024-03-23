package com.ahmrh.serene.domain.usecase.gamification

import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import javax.inject.Inject

class GetAchievementUseCase @Inject constructor(
    private val gamificationRepository: GamificationRepository
) {

    suspend operator fun invoke(
        achievementId: String, onSuccess: (Achievement) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) = gamificationRepository.fetchAchievementById(
        achievementId, onSuccess, onFailure
    )
}