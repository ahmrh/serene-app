package com.ahmrh.serene.ui.screen.main.activity.practice

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
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
    private val activityUseCases: ActivityUseCases
): ViewModel(){

    private var _activity: MutableState<SelfCareActivity?> = mutableStateOf(null)

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _activityDetailUiState

    private var _enabledPracticeButtonUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(true)

    val enabledPracticeButtonUiState: StateFlow<Boolean>
        get() = _enabledPracticeButtonUiState

    // Result
    private var _unlockedAchievementState: MutableStateFlow<String?> =
        MutableStateFlow(null)

    val unlockedAchievementState: StateFlow<String?>
        get() = _unlockedAchievementState


    init {
        initPracticeButton()
    }

    private fun initPracticeButton(){
        viewModelScope.launch{
            preferencesRepository.getStartedActivityIdValue().collect{
                _enabledPracticeButtonUiState.value = it == null
            }
        }
    }

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
        viewModelScope.launch {
            preferencesRepository.clearStartedActivityIdValue()
            activityUseCases.practiceSelfCare(_activity.value!!,
                onAchievementUnlocked = {
                    Log.d(TAG, "Achievement Id: $it")
                    if(it == null){
                        _unlockedAchievementState.value = "null"
                    } else {
                        _unlockedAchievementState.value = it
                    }
                },
                onResult = {
                    Log.e(TAG, "Error: $it")
                }
            )

        }
    }

    companion object{
        const val TAG = "PracticeViewModel"
    }

}