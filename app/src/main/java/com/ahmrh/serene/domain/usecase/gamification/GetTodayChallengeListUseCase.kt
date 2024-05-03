package com.ahmrh.serene.domain.usecase.gamification

import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.domain.model.gamification.Challenge
import javax.inject.Inject

class GetTodayChallengeListUseCase @Inject constructor(
    private val gamificationRepository: GamificationRepository
){
    suspend operator fun invoke(onSuccess: (List<Challenge>) -> Unit, onFailure: (Throwable) -> Unit){

        gamificationRepository.fetchTodayChallenges(
            onSuccess = onSuccess,
            onFailure = onFailure
        )

    }
}