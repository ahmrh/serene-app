package com.ahmrh.serene.ui.screen.main.setting

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.data.repository.WorkManagerRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val workManagerRepository: WorkManagerRepository
): ViewModel(){

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

        Log.d(TAG, "Notification: ${_notificationState.value}")
        Log.d(TAG, "Dark Mode: ${_darkModeState.value}")

    }

    private fun getDarkModeValue(){
        viewModelScope.launch {
            preferencesRepository.getDarkModeValue().collect{
                _darkModeState.value = it
            }
            Log.d(TAG, "Dark Mode: ${_darkModeState.value}")
        }
    }

    private fun getNotificationValue() {
        viewModelScope.launch {
            preferencesRepository.getNotificationValue().collect{
                _notificationState.value = it
            }
            Log.d(TAG, "Notification: ${_notificationState.value}")
        }
    }

     fun changeDarkModeValue(boolean: Boolean) {
         viewModelScope.launch {
             preferencesRepository.changeDarkModeValue(boolean)
             Log.d(TAG, "Dark Mode: ${_darkModeState.value}")
         }
         _darkModeState.value = boolean
     }

    fun changeNotificationValue(boolean: Boolean) {
        viewModelScope.launch {
            preferencesRepository.changeNotificationValue(boolean)
            Log.d(TAG, "Notification: ${_notificationState.value}")
        }
        _notificationState.value = boolean
    }

    fun scheduleNotification(){

        workManagerRepository.scheduleReminder(
            3L, TimeUnit.SECONDS
        )
    }

    fun saveChange(){

    }

    companion object {
        const val TAG = "SettingViewModel"

    }

}