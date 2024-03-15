package com.ahmrh.serene.domain.usecase.auth

import androidx.compose.runtime.collectAsState
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.data.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SignInAnonymouslyUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(onResult: (Throwable?) -> Unit)
        = userRepository.createAnonymousAccount(onResult)
}