package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PersonalizationRepository
import javax.inject.Inject

class SavePersonalizationResultUseCase @Inject constructor(
    private val personalizationRepository: PersonalizationRepository,
    private val gamificationRepository: GamificationRepository
){

    suspend operator fun invoke(category: Category){
        personalizationRepository.savePersonalizationResult(category)
        gamificationRepository.addChallengeProgress(ChallengeType.PERSONALIZATION)
    }
}