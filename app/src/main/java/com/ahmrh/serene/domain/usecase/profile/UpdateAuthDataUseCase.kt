package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.user.Auth
import javax.inject.Inject

class UpdateAuthDataUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(auth: Auth, onSuccess: () -> Unit, onFailure: (Throwable?) -> Unit){

    }


}