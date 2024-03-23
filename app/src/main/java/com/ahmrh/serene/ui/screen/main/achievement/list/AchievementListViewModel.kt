package com.ahmrh.serene.ui.screen.main.achievement.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.usecase.gamification.GamificationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementListViewModel @Inject constructor(
    private val gamificationUseCases: GamificationUseCases
): ViewModel() {

    private var _achievementListUiState: MutableStateFlow<UiState<List<Achievement>>>
            = MutableStateFlow(UiState.Loading)
    val achievementListUiState: StateFlow<UiState<List<Achievement>>>
        get() = _achievementListUiState

    init{
        getAchievementList()
    }
    private fun getAchievementList() =
        viewModelScope.launch {

            gamificationUseCases.getAchievementList(
                onFailure = {
                    _achievementListUiState.value = UiState.Error("${it?.localizedMessage}")

                },
                onSuccess = {
                    _achievementListUiState.value = UiState.Success(it)

                }
            )
        }


    companion object{
        const val TAG = "AchievementListViewModel"
    }

}