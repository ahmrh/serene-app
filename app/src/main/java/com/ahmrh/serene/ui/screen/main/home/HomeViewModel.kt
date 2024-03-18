package com.ahmrh.serene.ui.screen.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private var _selfCareStartedUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    val selfCareStartedUiState: StateFlow<Boolean>
        get() = _selfCareStartedUiState

    private  var _startedActivityIdState: MutableStateFlow<String?> =
        MutableStateFlow(null)

    val startedActivityIdState: StateFlow<String?>
        get() = _startedActivityIdState

    private var _personalizationResultState: MutableStateFlow<Category?> =
        MutableStateFlow(null)

    val personalizationResultState: StateFlow<Category?>
        get() = _personalizationResultState

    private var _currentUser: MutableStateFlow<FirebaseUser?> = MutableStateFlow(Firebase.auth.currentUser)

    val currentUser: StateFlow<FirebaseUser?>
        get() = _currentUser


    private fun getPersonalizationResult(){
        viewModelScope.launch {

            preferencesRepository.getPersonalizationResultValue().collect{
                _personalizationResultState.value  = it
            }
        }
    }


    private fun getStartedActivityId(){

        viewModelScope.launch {
            // get data
            preferencesRepository.getStartedActivityIdValue().collect{
                _selfCareStartedUiState.value = it != null
                _startedActivityIdState.value = it
            }

        }
    }


    init {
        getPersonalizationResult()
        getStartedActivityId()

        Log.d(TAG, "Init : ${personalizationResultState.value} and ${selfCareStartedUiState.value}")
    }

    fun addSelfCareStartedValue() {
        viewModelScope.launch {
            preferencesRepository.changeStartedActivityIdValue("random id goes brr")
        }
    }

    fun changeSelfCareStartedValue(boolean: Boolean){
        viewModelScope.launch {
            preferencesRepository.changeSelfCareStartedValue(boolean);
            preferencesRepository.changeStartedActivityIdValue("random id goes brr")
        }
    }

    companion object{
        const val TAG = "HomeViewModel"
    }

}