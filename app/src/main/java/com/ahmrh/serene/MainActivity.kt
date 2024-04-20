package com.ahmrh.serene

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.navigation.SereneNavGraph
import com.ahmrh.serene.ui.screen.main.setting.SettingViewModel
import com.ahmrh.serene.ui.theme.SereneTheme
import dagger.hilt.android.AndroidEntryPoint

private val Context.dataStore by preferencesDataStore("preferences")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(

        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SettingViewModel = viewModel()
            navController = rememberNavController()
            SereneTheme(
                darkTheme = viewModel.darkModeState.value
            ) {
                SereneNavGraph(navController)
            }
        }

    }

}

