package com.ahmrh.serene.ui.screen.auth.landing

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmrh.serene.data.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel(){

    private val _isLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(Firebase.auth.currentUser != null)

    val isLoggedIn : StateFlow<Boolean>
        get() = _isLoggedIn

    init {
        Log.d("LandingViewModel", "Logged in as ${Firebase.auth.currentUser?.uid}")
    }

}