package com.ahmrh.serene.common.state

import java.lang.Exception


sealed class AuthUiState {
    object Idle: AuthUiState()
    object Loading: AuthUiState()

    object Success: AuthUiState()

    data class Error(val errorMessage: String) : AuthUiState()
}