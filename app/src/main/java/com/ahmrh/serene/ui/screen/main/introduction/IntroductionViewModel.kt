package com.ahmrh.serene.ui.screen.main.introduction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.checkerframework.checker.units.qual.m
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel  @Inject constructor(

): ViewModel(){
    private val _introIndexStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    val introIndexStateFlow: StateFlow<Int>
        get() = _introIndexStateFlow

    init {
    }

    fun nextIndex(){
         _introIndexStateFlow.value += 1
    }

    fun prevIndex(){
        _introIndexStateFlow.value -= 1
    }




}