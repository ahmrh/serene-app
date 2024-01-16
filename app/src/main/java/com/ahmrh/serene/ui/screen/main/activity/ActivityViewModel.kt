package com.ahmrh.serene.ui.screen.main.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(

) : ViewModel() {

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    private var _activityListUiState: MutableStateFlow<UiState<List<SelfCareActivity>>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _activityDetailUiState

    val activityListUiState: StateFlow<UiState<List<SelfCareActivity>>>
        get() = _activityListUiState

    init {
        viewModelScope.launch{
            // get data
        }
    }

    // other function

}