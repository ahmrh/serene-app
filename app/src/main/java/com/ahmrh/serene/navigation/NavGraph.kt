package com.ahmrh.serene.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmrh.serene.ui.screen.auth.landing.LandingScreen
import com.ahmrh.serene.ui.screen.auth.setup_profile.SetUpProfileScreen
import com.ahmrh.serene.ui.screen.auth.login.LoginScreen
import com.ahmrh.serene.ui.screen.auth.forgot_password.ForgotPasswordScreen
import com.ahmrh.serene.ui.screen.auth.register.RegisterScreen
import com.ahmrh.serene.ui.screen.main.activity.ActivityCategoryScreen
import com.ahmrh.serene.ui.screen.main.activity.detail.ActivityDetailScreen
import com.ahmrh.serene.ui.screen.main.activity.list.ActivityListScreen
import com.ahmrh.serene.ui.screen.main.home.HomeScreen
import com.ahmrh.serene.ui.screen.main.introduction.IntroductionScreen
import com.ahmrh.serene.ui.screen.main.activity.practice.PracticeScreen
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationScreen
import com.ahmrh.serene.ui.screen.main.profile.ProfileScreen
import com.ahmrh.serene.ui.screen.main.result.ResultScreen
import com.ahmrh.serene.ui.screen.main.setting.SettingScreen


@Composable
fun SereneNavGraph(
    navController: NavHostController = rememberNavController(),
){
    NavHost(navController = navController, startDestination = Destination.Auth.route) {
        navigation(startDestination = Destination.Auth.Landing.route, route = Destination.Auth.route){

            composable(Destination.Auth.Landing.route){
                LandingScreen(navController)
            }

            composable(Destination.Auth.SetUpProfile.route){
                SetUpProfileScreen(navController)
            }

            composable(Destination.Auth.Login.route){
                LoginScreen(navController)
            }

            composable(Destination.Auth.ForgotPassword.route){
                ForgotPasswordScreen(navController)
            }

            composable(Destination.Auth.Register.route){
                RegisterScreen(navController)
            }

        }

        navigation(startDestination = Destination.Serene.Home.route, route = Destination.Serene.route){
            composable(Destination.Serene.Home.route){
                HomeScreen(navController)
            }

            composable(Destination.Serene.ActivityCategory.route){
                ActivityCategoryScreen(navController)
            }
            composable(Destination.Serene.ActivityList.route,  arguments = listOf(navArgument("categoryId") { defaultValue = "1" })){

                ActivityListScreen(navController, it.arguments?.getString("categoryId")?.toInt() ?: 1)
            }

            composable(Destination.Serene.ActivityDetail.route, arguments =  listOf(navArgument("activityId") { defaultValue = "null" })){
                ActivityDetailScreen(navController, it.arguments?.getString("activityId") ?: "null")
            }

            composable(Destination.Serene.Practice.route,  arguments =  listOf(navArgument("activityId") { defaultValue = "null" })){

                PracticeScreen(navController, it.arguments?.getString("activityId") ?: "null")
            }

            composable(Destination.Serene.Personalization.route){
                PersonalizationScreen(navController)
            }

            composable(Destination.Serene.Profile.route){
                ProfileScreen(navController)
            }

            composable(Destination.Serene.Result.route, arguments = listOf(navArgument("category"){ defaultValue = "1" })){

                ResultScreen(navController, it.arguments?.getString("categoryId")?.toInt() ?: 1)
            }

            composable(Destination.Serene.Introduction.route){
                IntroductionScreen(navController)
            }

            composable(Destination.Serene.Setting.route){
                SettingScreen(navController)
            }
        }



    }

}