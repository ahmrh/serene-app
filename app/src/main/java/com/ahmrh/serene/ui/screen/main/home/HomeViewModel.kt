package com.ahmrh.serene.ui.screen.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.ahmrh.serene.domain.model.selfcare.SelfCareRecommendation
import com.ahmrh.serene.domain.usecase.gamification.GamificationUseCases
import com.ahmrh.serene.domain.usecase.profile.UserProfileUseCases
import com.ahmrh.serene.domain.usecase.selfcare.activity.ActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val activityUseCases: ActivityUseCases,
    private val profileUseCases: UserProfileUseCases,
    private val gamificationUseCases: GamificationUseCases
) : ViewModel() {

    private var _firstTimeOpenedState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    val firstTimeOpenedState: MutableStateFlow<Boolean> =
        _firstTimeOpenedState

    private var _selfCareStartedUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    val selfCareStartedUiState: StateFlow<Boolean>
        get() = _selfCareStartedUiState

    private var _startedActivityIdState: MutableStateFlow<String?> =
        MutableStateFlow(null)

    val startedActivityIdState: StateFlow<String?>
        get() = _startedActivityIdState

    private var _personalizationResultState: MutableStateFlow<Category?> =
        MutableStateFlow(null)

    val personalizationResultState: StateFlow<Category?>
        get() = _personalizationResultState

    private var _usernameState: MutableStateFlow<String?> =
        MutableStateFlow("null")

    val usernameState: StateFlow<String?> = _usernameState


    private var _recommendationSelfCareState: MutableStateFlow<UiState<List<SelfCareRecommendation>?>> =
        MutableStateFlow(UiState.Loading)
    val recommendationSelfCareState: StateFlow<UiState<List<SelfCareRecommendation>?>>
        get() = _recommendationSelfCareState

    private var _challengeListState: MutableStateFlow<UiState<List<Challenge>>> =
        MutableStateFlow(UiState.Loading)
    val challengeListState: StateFlow<UiState<List<Challenge>>>
        get() = _challengeListState



    init {
        getPersonalizationResult()
        getFirstTimeOpened()

        getStartedActivityId()
        getRecommendation()

        getUsername()
        getChallengeList()
    }

    private fun getChallengeList(){
        viewModelScope.launch {
            personalizationResultState.value.let { category ->
                gamificationUseCases.getTodayChallengeList( category,
                    onSuccess = {
                        _challengeListState.value = UiState.Success(it)
                    },
                    onFailure = {
                        _challengeListState.value = UiState.Error(it.localizedMessage ?: "Unexpected Error")
                        Log.e(TAG, "Error getting challenge: $it")
                    }
                )
            }
        }
    }

    private fun getRecommendation() =
        viewModelScope.launch {
            personalizationResultState.value?.let { category ->
                activityUseCases.getRecommendation(category).collect {
                    when (it) {
                        is ResourceState.Success -> {
                            _recommendationSelfCareState.value =
                                UiState.Success(
                                    it.data as List<SelfCareRecommendation>
                                )
                        }

                        is ResourceState.Failed -> {
                            _recommendationSelfCareState.value =
                                UiState.Error("Error: ${it.exception}")
                        }
                    }
                }
            }

        }

    private fun getUsername() =
        viewModelScope.launch {
            profileUseCases.getUsername(
                onSuccess = {
                    _usernameState.value = it
                },
                onFailure = {
                    Log.e(TAG, "error anjir $it")
                    _usernameState.value = "Error"
                }
            )

        }

    private fun getPersonalizationResult() {
        viewModelScope.launch {

            preferencesRepository.getPersonalizationResultValue().collect {
                _personalizationResultState.value = it
            }
        }
    }

    private fun getFirstTimeOpened() {
        viewModelScope.launch {

            preferencesRepository.getFirstTimeValue().collect {
                _firstTimeOpenedState.value = it
            }
        }
    }


    private fun getStartedActivityId() {

        viewModelScope.launch {
            // get data
            preferencesRepository.getStartedActivityIdValue().collect {
                _selfCareStartedUiState.value = it != null
                _startedActivityIdState.value = it
            }

        }
    }


    companion object {
        const val TAG = "HomeViewModel"
    }

}