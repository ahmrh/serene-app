package com.ahmrh.serene.domain.usecase.auth

import javax.inject.Inject

data class AuthUseCases @Inject constructor(
    val signInWithEmail: SignInWithEmailUseCase,
    val signInAnonymously: SignInAnonymouslyUseCase,
    val recoverPassword: PasswordRecoveryUseCase,
    val signUpWithEmail: SignUpWithEmailUseCase,

)
