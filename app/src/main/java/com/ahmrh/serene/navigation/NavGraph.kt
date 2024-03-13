package com.ahmrh.serene.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmrh.serene.ui.screen.main.activity.ActivityCategoryScreen
import com.ahmrh.serene.ui.screen.main.activity.detail.ActivityDetailScreen
import com.ahmrh.serene.ui.screen.main.activity.list.ActivityListScreen
import com.ahmrh.serene.ui.screen.main.home.HomeScreen
import com.ahmrh.serene.ui.screen.main.introduction.IntroductionScreen
import com.ahmrh.serene.ui.screen.main.activity.practice.PracticeScreen
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationScreen
import com.ahmrh.serene.ui.screen.main.profile.ProfileScreen
import com.ahmrh.serene.ui.screen.main.result.ResultScreen


@Composable
fun SereneNavGraph(
    navController: NavHostController = rememberNavController(),
){
    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route){
            HomeScreen(navController)
        }

        composable(Destination.ActivityCategory.route){

            ActivityCategoryScreen(navController)
        }
        composable(Destination.ActivityList.route,  arguments = listOf(navArgument("categoryId") { defaultValue = "1" })){

            ActivityListScreen(navController, it.arguments?.getString("categoryId")?.toInt() ?: 1)
        }

        composable(Destination.ActivityDetail.route, arguments =  listOf(navArgument("activityId") { defaultValue = "null" })){
            ActivityDetailScreen(navController, it.arguments?.getString("activityId") ?: "null")
        }

        composable(Destination.Practice.route,  arguments =  listOf(navArgument("activityId") { defaultValue = "null" })){

            PracticeScreen(navController, it.arguments?.getString("activityId") ?: "null")
        }

        composable(Destination.Personalization.route){
            PersonalizationScreen(navController)
        }

        composable(Destination.Profile.route){
            ProfileScreen(navController)
        }

        composable(Destination.Result.route, arguments = listOf(navArgument("category"){ defaultValue = "1" })){

            ResultScreen(navController, it.arguments?.getString("categoryId")?.toInt() ?: 1)
        }


        composable(Destination.Introduction.route){
            IntroductionScreen(navController)
        }
    }

}