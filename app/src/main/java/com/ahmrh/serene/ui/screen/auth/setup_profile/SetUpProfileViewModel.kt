package com.ahmrh.serene.ui.screen.auth.setup_profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SetUpProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel(){
    val uiState: MutableState<AuthUiState> = mutableStateOf(AuthUiState.Idle)

    fun signInAnonymously(){
        uiState.value = AuthUiState.Loading
        
        authUseCases.signInAnonymously{
            if(it == null){
                uiState.value = AuthUiState.Success
            } else {
                uiState.value = AuthUiState.Error(it.message.toString())
            }

        }
    }
}