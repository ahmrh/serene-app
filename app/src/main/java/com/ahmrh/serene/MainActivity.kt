package com.ahmrh.serene

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
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
            val systemDarkTheme = isSystemInDarkTheme()
            val viewModel: SettingViewModel = viewModel()
            viewModel.changeDarkModeValue(systemDarkTheme)

            navController = rememberNavController()
            SereneTheme(
                darkTheme = viewModel.darkModeState.value
            ) {
                SereneNavGraph(navController)
            }
        }

    }

}


fun Activity.openAppSettings(){
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)

}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}