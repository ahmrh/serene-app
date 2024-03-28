package com.ahmrh.serene.ui.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.user.Profile
import com.ahmrh.serene.domain.usecase.gamification.GamificationUseCases
import com.ahmrh.serene.domain.usecase.profile.UserProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val preferencesRepository: PreferencesRepository,
    private val gamificationUseCases: GamificationUseCases,
    private val profileUseCases: UserProfileUseCases
): ViewModel() {

    private var _profileDataUiState: MutableStateFlow<UiState<Profile>> = MutableStateFlow(UiState.Loading)

    val profileDataUiState: StateFlow<UiState<Profile>>
        get() = _profileDataUiState

    init {
        getProfileData()
    }

    private fun getProfileData(){
        viewModelScope.launch {
            profileUseCases.getProfileData(
                onSuccess = {
                    _profileDataUiState.value = UiState.Success(it)
                },
                onFailure = {
                    _profileDataUiState.value = UiState.Error(it?.message ?: "Unexpected error")
                }
            )
        }
    }
    companion object  {
        const val TAG = "ProfileViewModel"
    }

}