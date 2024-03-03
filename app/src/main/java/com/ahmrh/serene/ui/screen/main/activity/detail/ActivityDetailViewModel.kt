package com.ahmrh.serene.ui.screen.main.activity.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.SelfCareActivity
import com.ahmrh.serene.domain.usecase.selfcare.activity.ActivityUseCases
import com.ahmrh.serene.ui.screen.main.activity.list.ActivityListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val activityUseCases: ActivityUseCases,
    private val preferencesRepository: PreferencesRepository
): ViewModel(){

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _activityDetailUiState

    private var _enabledPracticeButtonUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(true)

    val enabledPracticeButtonUiState: StateFlow<Boolean>
        get() = _enabledPracticeButtonUiState

    private  var _startedActivityIdState: MutableStateFlow<String?> = MutableStateFlow(null)

    val startedActivityIdState: StateFlow<String?>
        get() = _startedActivityIdState

    init {
        initPracticeButton()
    }

    private fun initPracticeButton(){
        viewModelScope.launch{
            preferencesRepository.getStartedActivityIdValue().collect{
                _enabledPracticeButtonUiState.value = it == null

                _startedActivityIdState.value = it

            }
        }
    }

    fun getActivityDetail(id : String) =
        viewModelScope.launch {

            activityUseCases.getActivity(id).collect{
                when(it){
                    is ResourceState.Success -> {
                        _activityDetailUiState.value = UiState.Success(it.data as SelfCareActivity)
                        Log.d(ActivityListViewModel.TAG, "Getting activity detail: ${_activityDetailUiState.value}")
                    }
                    is ResourceState.Failed -> {
                        _activityDetailUiState.value = UiState.Error(it.exception?.message ?: "Unexpected Error")
                        Log.e(ActivityListViewModel.TAG, "${it.exception}")
                    }
                }
            }
        }

    fun changeStartedActivityIdValue(id: String){
        viewModelScope.launch {
            preferencesRepository.changeStartedActivityIdValue(id)
        }
    }

}