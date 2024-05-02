package com.ahmrh.serene.ui.screen.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    val uiState: MutableState<AuthUiState> = mutableStateOf(AuthUiState.Idle)

    fun onLogin(email: String, password: String){
        Log.d(TAG, "Email: $email, Password: $password")
        uiState.value = AuthUiState.Loading

        userRepository.authenticate(email, password){
            if(it == null) uiState.value = AuthUiState.Success
            else{
                Log.d(TAG, "Error: ${it.message}")
                uiState.value = AuthUiState.Error(it.message ?: "Unexpected error")
            }
        }
    }

    companion object{
        const val TAG = "LoginViewModel"
    }

}


