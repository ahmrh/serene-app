package com.ahmrh.serene

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.screen.main.activity.ActivityCategoryScreen
import com.ahmrh.serene.ui.screen.main.activity.ActivityDetailScreen
import com.ahmrh.serene.ui.screen.main.activity.ActivityListScreen
import com.ahmrh.serene.ui.screen.main.home.HomeScreen
import com.ahmrh.serene.ui.screen.main.personalization.QuestionScreen
import com.ahmrh.serene.ui.screen.main.personalization.ResultScreen
import com.ahmrh.serene.ui.screen.main.practice.PracticeScreen
import com.ahmrh.serene.ui.screen.main.profile.ProfileScreen
import com.ahmrh.serene.ui.theme.SereneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route){
            HomeScreen(navController)
        }

        composable(Destination.ActivityCategory.route){
            ActivityCategoryScreen(navController)
        }
        composable(Destination.ActivityList.route,  arguments = listOf(navArgument("categoryId") { defaultValue = "1" })){
            val categoryId = it.arguments?.getString("categoryId")?.toInt() ?: 1
            Log.d("qiwjeiqw", "CategoryId = $categoryId")
            ActivityListScreen(navController, categoryId)
        }

        composable(Destination.ActivityDetail.route){

            ActivityDetailScreen(navController)
        }

        composable(Destination.Practice.route){

            PracticeScreen(navController)
        }

        composable(Destination.Profile.route){
            ProfileScreen(navController)
        }

        composable(Destination.Question.route){
            QuestionScreen(navController)
        }
        composable(Destination.Result.route){

            ResultScreen(navController)
        }
    }

}
