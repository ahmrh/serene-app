package com.ahmrh.serene.domain.usecase.selfcare.activity

import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import javax.inject.Inject

class PracticeSelfCareUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val gamificationRepository: GamificationRepository
) {
    operator fun invoke(selfCareActivity: SelfCareActivity, onResult: (Throwable?) -> Unit){
        gamificationRepository.fetchAchievements{}
        userRepository.addSelfCareHistory(selfCareActivity.id, onResult)

    }

}