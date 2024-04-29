package com.ahmrh.serene.ui.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.user.Profile
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.domain.model.user.UserStats
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

    private var _profileUiState: MutableStateFlow<UiState<Profile>> = MutableStateFlow(UiState.Loading)
    val profileUiState: StateFlow<UiState<Profile>>
        get() = _profileUiState


    private var _userStatsUiState : MutableStateFlow<UiState<UserStats>> = MutableStateFlow(UiState.Loading)
    val userStatsUiState: StateFlow<UiState<UserStats>>
        get() = _userStatsUiState


    private var _historyListUiState: MutableStateFlow<UiState<List<SelfCareHistory>>> = MutableStateFlow(UiState.Loading)
    val historyListUiState: StateFlow<UiState<List<SelfCareHistory>>>
        get() = _historyListUiState

    private var _achievementListUiState: MutableStateFlow<UiState<List<Achievement>>> = MutableStateFlow(UiState.Loading)
    val achievementListUiState: StateFlow<UiState<List<Achievement>>>
        get() = _achievementListUiState

    init {
        getProfileData()
        getHistoryList()
        getAchievementList()
        getUserStats()
    }

    private fun getProfileData(){
        viewModelScope.launch {
            profileUseCases.getProfileData(
                onSuccess = {
                    _profileUiState.value = UiState.Success(it)
                },
                onFailure = {
                    _profileUiState.value = UiState.Error(it?.message ?: "Unexpected error")
                }
            )
        }
    }

    private fun getHistoryList(){
        viewModelScope.launch {
            profileUseCases.getSelfCareHistory(
                onSuccess = {
                    _historyListUiState.value = UiState.Success(it)
                },
                onFailure = {

                    _historyListUiState.value = UiState.Error(it?.message ?: "Unexpected error")
                }
            )

        }

    }

    private fun getAchievementList(){
        viewModelScope.launch{
            gamificationUseCases.getAchievementList(
                onSuccess = {
                    _achievementListUiState.value = UiState.Success(it)
                },
                onFailure = {

                    _achievementListUiState.value = UiState.Error(it?.message ?: "Unexpected error")
                }
            )

        }
    }

    private fun getUserStats() {

        viewModelScope.launch {
            profileUseCases.getUserStats(
                onSuccess = {
                    _userStatsUiState.value = UiState.Success(it)
                },
                onFailure = {

                    _userStatsUiState.value = UiState.Error(it?.message ?: "Unexpected error")
                }
            )

        }
    }

    companion object  {
        const val TAG = "ProfileViewModel"
    }

}