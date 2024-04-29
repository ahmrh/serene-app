package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import javax.inject.Inject

class GetSelfCareHistoryUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(
        onSuccess: (List<SelfCareHistory>) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        try{
            val selfCareHistory = userRepository.fetchSelfCareHistoryList()!!
            onSuccess(selfCareHistory)
        } catch(e: Exception) {
            onFailure(e)
        }


    }
}