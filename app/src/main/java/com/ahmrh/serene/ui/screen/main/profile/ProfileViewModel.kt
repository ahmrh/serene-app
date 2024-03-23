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
    // continue this
//    private var _userDataUiState: MutableStateFlow<>
//
//    private var _joinedUiState: MutableStateFlow<>

    private var _profileDataUiState: MutableStateFlow<UiState<Profile>> = MutableStateFlow(UiState.Loading)

    val profileDataUiState: StateFlow<UiState<Profile>>
        get() = _profileDataUiState

//
//    private var _dailyStreakUiState: MutableStateFlow<Int?> = MutableStateFlow(null)
//    val dailyStreakUiState: StateFlow<Int?>
//        get() = _dailyStreakUiState
//
//    private var _totalAchievementUiState: MutableStateFlow<Int?> = MutableStateFlow(null)
//    val totalAchievementUiState: StateFlow<Int?>
//        get() = _totalAchievementUiState
//
//
//    private var _topSelfCareUiState: MutableStateFlow<Category?> = MutableStateFlow(null)
//    val topSelfCareUiState: StateFlow<Category?>
//        get() = _topSelfCareUiState
//
//
//    private var _totalSelfCareUiState: MutableStateFlow<Int?> = MutableStateFlow(null)
//    val totalSelfCareUiState: StateFlow<Int?>
//        get() = _totalSelfCareUiState
//
//
//    private var _historyListUiState: MutableStateFlow<List<SelfCareHistory>> = MutableStateFlow(listOf())
//    val historyListUiState: StateFlow<List<SelfCareHistory>>
//        get() = _historyListUiState
//
//    private var _achievementListUiState: MutableStateFlow<List<Achievement>> = MutableStateFlow(listOf())
//    val achievementListUiState: StateFlow<List<Achievement>>
//        get() = _achievementListUiState

    init {
        getProfileData()
    }

//    private fun getAchievement(){
//
//        viewModelScope.launch {
//            gamificationUseCases.getAchievementList(
//                onSuccess = {
//                    _achievementListUiState.value = it
//                },
//                onFailure = {
//                    Log.e(TAG, "Failure: $it")
//                }
//            )
//        }
//    }

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