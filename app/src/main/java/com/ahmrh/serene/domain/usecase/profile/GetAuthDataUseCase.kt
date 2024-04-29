package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.user.Auth
import com.ahmrh.serene.domain.model.user.Profile
import javax.inject.Inject

class GetAuthDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(
        onSuccess: (Profile) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){
//        userRepository.getAuthData(
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )

    }
}