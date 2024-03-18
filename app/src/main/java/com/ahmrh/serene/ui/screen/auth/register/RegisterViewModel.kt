package com.ahmrh.serene.ui.screen.auth.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    val uiState: MutableState<AuthUiState> = mutableStateOf(AuthUiState.Idle)

    fun onRegister(email: String, password: String){
        userRepository.createAccount(email, password){
            if(it == null) uiState.value = AuthUiState.Success
            else uiState.value = AuthUiState.Error(it.message ?: "Unexpected Error")
        }
    }

    companion object{
        const val TAG = "RegisterViewModel"
    }

}