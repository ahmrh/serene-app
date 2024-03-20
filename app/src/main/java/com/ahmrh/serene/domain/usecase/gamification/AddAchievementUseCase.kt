package com.ahmrh.serene.domain.usecase.gamification

import com.ahmrh.serene.data.repository.GamificationRepository
import javax.inject.Inject

class AddAchievementUseCase @Inject constructor(
    private val gamificationRepository: GamificationRepository
){
    operator fun invoke(){
        gamificationRepository
    }
}