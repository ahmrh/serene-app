package com.ahmrh.serene.domain.usecase.auth

import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.SelfCareActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInAnonymouslyUseCase@Inject constructor(
    private val userRepository: UserRepository
)  {

    operator fun invoke() {

        userRepository.createAnonymousAccount()
    }
}