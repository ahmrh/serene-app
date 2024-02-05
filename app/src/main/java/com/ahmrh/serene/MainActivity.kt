package com.ahmrh.serene

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.navigation.SereneNavGraph
import com.ahmrh.serene.ui.screen.main.activity.ActivityCategoryScreen
import com.ahmrh.serene.ui.screen.main.activity.ActivityDetailScreen
import com.ahmrh.serene.ui.screen.main.activity.ActivityListScreen
import com.ahmrh.serene.ui.screen.main.home.HomeScreen
import com.ahmrh.serene.ui.screen.main.introduction.IntroductionScreen
import com.ahmrh.serene.ui.screen.main.personalization.ResultScreen
import com.ahmrh.serene.ui.screen.main.personalization.question.BaseQuestionScreen
import com.ahmrh.serene.ui.screen.main.practice.PracticeScreen
import com.ahmrh.serene.ui.screen.main.profile.ProfileScreen
import com.ahmrh.serene.ui.theme.SereneTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.Preferences

private val Context.dataStore by preferencesDataStore("preferences")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            SereneTheme {
                SereneNavGraph(navController)
            }
        }

    }

}

