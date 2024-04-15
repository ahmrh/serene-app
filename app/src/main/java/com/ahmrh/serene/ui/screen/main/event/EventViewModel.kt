package com.ahmrh.serene.ui.screen.main.event

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.enums.Event
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.handler.EventHandler
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.gamification.DailyStreak
import com.ahmrh.serene.domain.usecase.gamification.GamificationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val gamificationUseCases: GamificationUseCases,
    private val eventHandler: EventHandler
): ViewModel()  {
    companion object{
        const val TAG = "EventViewModel"
    }

    private val _achievementEventUiState: MutableStateFlow<UiState<Achievement>> = MutableStateFlow(UiState.Loading)
    val achievementEventUiState: StateFlow<UiState<Achievement>>
        get() = _achievementEventUiState

    private val _dailyStreakEventState: MutableState<DailyStreak?> = mutableStateOf(null)
    val dailyStreakEventState: State<DailyStreak?>
        get() = _dailyStreakEventState

    val dailyStreakEventOpen: MutableState<Boolean> = mutableStateOf(false)
    val achievementEventOpen: MutableState<Boolean> = mutableStateOf(false)


    init {

        while(!eventHandler.isEmpty()){
            when(val event = eventHandler.popEvent()){

                is Event.DailyStreakEvent -> {
                    dailyStreakEventOpen.value = true
                    // Access properties of DailyStreakEvent
                    val dailyStreak = event.dailyStreak

                    _dailyStreakEventState.value = dailyStreak
                }
                is Event.AchievementEvent -> {
                    achievementEventOpen.value = true
                    // Access properties of AchievementEvent
                    val achievementId = event.achievementId

                    getAchievement(achievementId)
                }
            }
        }

        Log.d(TAG, "Initializing ${achievementEventOpen.value} ${dailyStreakEventOpen.value}")
    }

    private fun getAchievement(achievementId: String){
        viewModelScope.launch{
            gamificationUseCases.getAchievement(
                achievementId = achievementId,
                onSuccess = {
                    _achievementEventUiState.value = UiState.Success(it)
                },
                onFailure = {

                    _achievementEventUiState.value = UiState.Error(it?.localizedMessage ?: "Unexpected Error")
                }
            )
        }
    }

}