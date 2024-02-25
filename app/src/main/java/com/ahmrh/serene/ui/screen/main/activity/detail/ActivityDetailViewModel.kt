package com.ahmrh.serene.ui.screen.main.activity.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.SelfCareActivity
import com.ahmrh.serene.domain.usecase.selfcare.SelfCareUseCases
import com.ahmrh.serene.ui.screen.main.activity.list.ActivityListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val selfCareUseCases: SelfCareUseCases
): ViewModel(){

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _activityDetailUiState

    fun getActivityDetail(id : String) =
        viewModelScope.launch {

            selfCareUseCases.getActivity(id).collect{
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

}