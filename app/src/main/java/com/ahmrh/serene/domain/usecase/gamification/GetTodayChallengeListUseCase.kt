package com.ahmrh.serene.domain.usecase.gamification

import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.domain.handler.ChallengeHandler
import com.ahmrh.serene.domain.model.gamification.Challenge
import javax.inject.Inject

class GetTodayChallengeListUseCase @Inject constructor(
    private val gamificationRepository: GamificationRepository,
    private val challengeHandler: ChallengeHandler
){
    suspend operator fun invoke(personalizationCategory: Category?, onSuccess: (List<Challenge>) -> Unit, onFailure: (Throwable) -> Unit){

//        gamificationRepository.addTodayChallenge(personalizationCategory)

        gamificationRepository.fetchTodayChallenge(
            personalizationCategory = personalizationCategory,
            onSuccess = onSuccess,
            onFailure = onFailure
        )

//        try{
//            val challengeList = gamificationRepository.generateTodayChallenge(personalizationCategory)
//            onSuccess(challengeList)
//        } catch (e: Exception){
//            onFailure(e)
//        }

    }

//    suspend operator fun invoke(personalizationCategory: Category?): List<Challenge>
//        = gamificationRepository.generateTodayChallenge(personalizationCategory)

}