package com.ahmrh.serene.ui.screen.main.activity.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.SelfCareActivity
import com.ahmrh.serene.domain.usecase.selfcare.SelfCareUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityListViewModel @Inject constructor(
    private val selfCareUseCases: SelfCareUseCases

) : ViewModel() {



    private var _activityListUiState: MutableStateFlow<UiState<List<SelfCareActivity>>> =
        MutableStateFlow(UiState.Loading)


    val activityListUiState: StateFlow<UiState<List<SelfCareActivity>>>
        get() = _activityListUiState


    fun getActivityByCategory(category: Category) =
        viewModelScope.launch{
            // get data
            Log.d(TAG, "Fetching data for category: $category")

            selfCareUseCases.getActivities(category).collect{
                when(it){
                    is ResourceState.Success -> {
                        _activityListUiState.value = UiState.Success(it.data as List<SelfCareActivity>)
                    }
                    is ResourceState.Failed -> {
                        _activityListUiState.value = UiState.Error(it.exception?.message ?: "Unexpected Error")
                        Log.e(TAG, "${it.exception}")
                    }
                }
            }
        }

    // other function

    companion object{
        const val TAG = "ActivityListViewModel"
    }
}