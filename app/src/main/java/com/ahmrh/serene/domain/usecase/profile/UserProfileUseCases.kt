package com.ahmrh.serene.domain.usecase.profile

import javax.inject.Inject

data class UserProfileUseCases @Inject constructor(

    val getUsername: GetUsernameUseCase,
    val getProfileData: GetProfileDataUseCase
)