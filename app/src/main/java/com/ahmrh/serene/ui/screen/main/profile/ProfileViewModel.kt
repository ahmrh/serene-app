package com.ahmrh.serene.ui.screen.main.profile

import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {

    val _userProfileUiState: MutableStateFlow<UiState<Nothing>>
            = MutableStateFlow(UiState.Loading)

    val userProfileUiState: StateFlow<UiState<Nothing>>
        get() = _userProfileUiState
}