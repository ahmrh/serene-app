package com.ahmrh.serene

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.navigation.Screen
import com.ahmrh.serene.ui.screen.main.activity.ActivityScreen
import com.ahmrh.serene.ui.screen.main.home.HomeScreen
import com.ahmrh.serene.ui.screen.main.profile.ProfileScreen
import com.ahmrh.serene.ui.theme.SereneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            SereneTheme {
                SereneApp()
            }
        }
    }
}

@Composable
fun SereneApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route){
            HomeScreen(navController)

        }
        composable(Screen.Activity.route){
            ActivityScreen(navController)
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController)

        }
    }

}
