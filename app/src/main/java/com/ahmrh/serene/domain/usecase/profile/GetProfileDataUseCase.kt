package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.user.Profile
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(onSuccess: (Profile) -> Unit, onFailure: (Throwable?) -> Unit)
            = userRepository.fetchProfileData(onSuccess, onFailure)
}