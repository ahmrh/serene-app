package com.ahmrh.serene.ui.screen.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.SelfCareActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {


//    private var _recommendationlUiState: MutableStateFlow<UiState<List<SelfCareActivity>>> =
//        MutableStateFlow(UiState.Loading)
//
//    val recommendationlUiState: StateFlow<UiState<List<SelfCareActivity>>>
//        get() = _recommendationlUiState

    init {
        viewModelScope.launch {
            // get data
        }

    }


}