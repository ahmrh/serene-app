package com.ahmrh.serene.ui.screen.main.practice

import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.SelfCareActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(

): ViewModel(){


    private var _selfCareUiState: MutableStateFlow<UiState<SelfCareActivity>> =
        MutableStateFlow(UiState.Loading)

    val selfCareUiState: StateFlow<UiState<SelfCareActivity>>
        get() = _selfCareUiState

}