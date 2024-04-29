package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.common.utils.ArrayUtils
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.user.Profile
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.domain.model.user.UserStats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        onSuccess: (Profile) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){

        userRepository.fetchProfileData(
            onSuccess = onSuccess, onFailure = onFailure
        )


    }
}