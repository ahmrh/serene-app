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

        gamificationRepository.addTodayChallenge(personalizationCategory)

        gamificationRepository.getTodayChallenge(
            onSuccess = onSuccess,
            onFailure = onFailure
        )

//        gamificationRepository.getTodayChallenges(null,
//            onSuccess = onSuccess, onFailure = onFailure
//        )

//        val todayChallengeListState = challengeHandler.todayChallengeList
//
//        onSuccess(todayChallengeListState)

//        try{
//        } catch(e: Exception){
//            Log.e("GetTodayChallengeListUseCase", "$e")
//        }
    }

}