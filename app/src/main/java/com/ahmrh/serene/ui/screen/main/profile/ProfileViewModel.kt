package com.ahmrh.serene.ui.screen.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    val _userProfileUiState: MutableStateFlow<UiState<Nothing>>
            = MutableStateFlow(UiState.Loading)

    val userProfileUiState: StateFlow<UiState<Nothing>>
        get() = _userProfileUiState


    private var _selfCareStartedUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    val selfCareStartedUiState: StateFlow<Boolean>
        get() = _selfCareStartedUiState




    init {
        viewModelScope.launch {
            // get data

            preferencesRepository.getStartedActivityIdValue().collect{
                _selfCareStartedUiState.value = it != null
                Log.d(TAG, "value self-care activity exist $it")
            }
        }

    }

    fun clearSelfCareActivityValue(){
        viewModelScope.launch {
            preferencesRepository.clearStartedActivityIdValue()
        }
    }

    fun changeSelfCareActivityIdValue(id: String){
        viewModelScope.launch {
            preferencesRepository.changeStartedActivityIdValue(id)
        }
    }

    companion object  {
        const val TAG = "ProfileViewModel"
    }

}