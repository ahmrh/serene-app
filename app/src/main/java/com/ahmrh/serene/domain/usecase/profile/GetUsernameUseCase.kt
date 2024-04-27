package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.UserRepository
import javax.inject.Inject

class GetUsernameUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(onSuccess: (String?) -> Unit, onFailure: (Throwable?) -> Unit)
        = userRepository.fetchUsername(onSuccess, onFailure)

}