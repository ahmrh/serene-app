package com.ahmrh.serene.ui.screen.main.setting

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(

): ViewModel(){
    fun signOut() = Firebase.auth.signOut()


}