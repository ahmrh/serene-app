package com.ahmrh.serene.ui.screen.main.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.UiState
import com.ahmrh.serene.data.source.local.room.entity.SelfCare
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(

) : ViewModel() {

    private var _activityDetailUiState: MutableStateFlow<UiState<SelfCare>> =
        MutableStateFlow(UiState.Loading)

    private var _activityListUiState: MutableStateFlow<UiState<List<SelfCare>>> =
        MutableStateFlow(UiState.Loading)

    val activityDetailUiState: StateFlow<UiState<SelfCare>>
        get() = _activityDetailUiState

    val activityListUiState: StateFlow<UiState<List<SelfCare>>>
        get() = _activityListUiState

    init {
        viewModelScope.launch{
            // get data
        }
    }

    // other function

}