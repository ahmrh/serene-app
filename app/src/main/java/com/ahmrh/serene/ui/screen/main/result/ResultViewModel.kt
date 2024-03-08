package com.ahmrh.serene.ui.screen.main.result

import androidx.lifecycle.ViewModel
import com.ahmrh.serene.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository
): ViewModel() {



}