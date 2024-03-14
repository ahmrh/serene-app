package com.ahmrh.serene.domain.usecase.auth

import com.ahmrh.serene.domain.usecase.selfcare.personalization.GetQuestionUseCase
import com.ahmrh.serene.domain.usecase.selfcare.personalization.GetResultUseCase
import javax.inject.Inject

data class AuthUseCases @Inject constructor(
    val signInWithEmail: SignInWithEmailUseCase,
    val signInAnonymously: SignInAnonymouslyUseCase,
    val recoverPassword: PasswordRecoveryUseCase,
    val signUpWithEmail: SignUpWithEmailUseCase
)
