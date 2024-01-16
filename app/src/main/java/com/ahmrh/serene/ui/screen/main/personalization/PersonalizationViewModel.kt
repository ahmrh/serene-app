package com.ahmrh.serene.ui.screen.main.personalization

import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(

): ViewModel() {

    private var _questionUiState: MutableStateFlow<UiState<Nothing>> =
        MutableStateFlow(UiState.Loading)

    val questionUiState: StateFlow<UiState<Nothing>>
        get() = _questionUiState



    private var _resultUiState: MutableStateFlow<UiState<Nothing>> =
        MutableStateFlow(UiState.Loading)

    val resultUiState: StateFlow<UiState<Nothing>>
        get() = _resultUiState

    init {

    }

}