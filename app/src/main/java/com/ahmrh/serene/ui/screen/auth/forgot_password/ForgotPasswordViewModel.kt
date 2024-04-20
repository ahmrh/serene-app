package com.ahmrh.serene.ui.screen.auth.forgot_password

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel(){

    fun recoverPassword(email: String){
        authUseCases.recoverPassword(email){ error ->
            error?.let {
                Log.e(TAG, "Recover password failed: ${it.message}")
            }
        }
    }

    companion object{
        const val TAG = "ForgotPasswordViewModel"
    }

}