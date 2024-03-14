package com.ahmrh.serene.domain.usecase.auth

import com.ahmrh.serene.data.repository.UserRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
}