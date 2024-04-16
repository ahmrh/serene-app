package com.ahmrh.serene.ui.screen.main.setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel(){

    // TODO : continue this, idk why its not working somehow
    fun signOut() = Firebase.auth.signOut()

    private var _darkModeState: MutableState<Boolean> = mutableStateOf(false)
    val darkModeState: State<Boolean>
        get() = _darkModeState
    

    private var _notificationState: MutableState<Boolean> = mutableStateOf(false)
    val notificationState: State<Boolean>
        get() = _notificationState


    init {
        getDarkModeValue()
        getNotificationValue()
    }

    private fun getDarkModeValue(){
        viewModelScope.launch {
            preferencesRepository.getDarkModeValue().collect{
                _darkModeState.value = it
            }
        }
    }

    private fun getNotificationValue() {
        viewModelScope.launch {
            preferencesRepository.getNotificationValue().collect{
                _notificationState.value = it
            }
        }
    }

     fun changeDarkModeValue(boolean: Boolean) {
         viewModelScope.launch {
             preferencesRepository.changeDarkModeValue(boolean)
         }
     }

    fun changeNotificationValue(boolean: Boolean) {
        viewModelScope.launch {
            preferencesRepository.changeDarkModeValue(!boolean)
        }
    }

}