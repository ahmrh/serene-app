package com.ahmrh.serene.ui.screen.main.achievement.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.usecase.gamification.GamificationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementDetailViewModel @Inject constructor(

    private val gamificationUseCases: GamificationUseCases,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private var _achievementUiState: MutableStateFlow<UiState<Achievement>>
        = MutableStateFlow(UiState.Loading)
    val achievementUiState: StateFlow<UiState<Achievement>>
        get() = _achievementUiState

    fun getAchievementDetail(achievementId : String) =
        viewModelScope.launch {

            gamificationUseCases.getAchievement(achievementId,
                onFailure = {
                    _achievementUiState.value = UiState.Error(it?.message ?: "Unexpected error")

                },
                onSuccess = {
                    _achievementUiState.value = UiState.Success(it)

                }
            )
        }

}