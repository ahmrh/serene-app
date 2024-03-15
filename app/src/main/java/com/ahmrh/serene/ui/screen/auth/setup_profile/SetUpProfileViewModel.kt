package com.ahmrh.serene.ui.screen.auth.setup_profile

import androidx.lifecycle.ViewModel
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
    private var _activityDetailUiState: MutableStateFlow<UiState<Boolean>?> =
        MutableStateFlow(null)

    val activityDetailUiState: StateFlow<UiState<Boolean>?>
        get() = _activityDetailUiState

    fun signInAnonymously(){
        authUseCases.signInAnonymously{
            if(it == null){
                _activityDetailUiState.value = UiState.Success(data = true)
            } else {
                _activityDetailUiState.value = UiState.Error(it.message.toString())
            }

        }
    }
}