package com.ahmrh.serene.ui.screen.main.activity.practice

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.enums.Event
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.handler.EventHandler
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.domain.usecase.selfcare.activity.ActivityUseCases
import com.ahmrh.serene.ui.screen.main.activity.list.ActivityListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(

    private val preferencesRepository: PreferencesRepository,
    private val activityUseCases: ActivityUseCases,
    private val eventHandler: EventHandler
): ViewModel(){

    private var _activity: MutableState<SelfCareActivity?> = mutableStateOf(null)

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _activityDetailUiState

    private val _eventStateLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    val eventStateLiveData: LiveData<Boolean> = _eventStateLiveData


    fun getActivityDetail(id : String) =
        viewModelScope.launch {
            activityUseCases.getActivity(id).collect{
                when(it){
                    is ResourceState.Success -> {
                        _activityDetailUiState.value = UiState.Success(it.data as SelfCareActivity)
                        _activity.value = it.data
                        Log.d(ActivityListViewModel.TAG, "Getting activity detail: ${_activityDetailUiState.value}")
                    }
                    is ResourceState.Failed -> {
                        _activityDetailUiState.value = UiState.Error(it.exception?.message ?: "Unexpected Error")
                        Log.e(ActivityListViewModel.TAG, "${it.exception}")
                    }
                }
            }
        }

    fun onPracticeDone(){
        clearStartedActivity()
        practiceSelfCare()
    }

    private fun clearStartedActivity(){
        viewModelScope.launch {
            preferencesRepository.clearStartedActivityIdValue()
        }
    }

    private fun practiceSelfCare(){
        viewModelScope.launch {
            activityUseCases.practiceSelfCare(_activity.value!!,
                onAchievementUnlockedEvent = {
                    if(it != null){
                        val event = Event.AchievementEvent(it)
                        eventHandler.addEvent(event)
                    }
                },
                onDailyStreakEvent = {

                    if(it != null){
                        val event = Event.DailyStreakEvent(it)
                        eventHandler.addEvent(event)
                    }
                },

                onResult = {
                    Log.e(TAG, "Error: $it")
                }
            )

            _eventStateLiveData.value = true
        }

    }

    companion object{
        const val TAG = "PracticeViewModel"
    }

}