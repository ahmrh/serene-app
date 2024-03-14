package com.ahmrh.serene.domain.usecase.auth

import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.data.repository.UserRepository
import javax.inject.Inject

class SignUpWithEmailUseCase  @Inject constructor(
    private val userRepository: UserRepository
)  {
}