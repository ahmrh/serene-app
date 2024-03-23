package com.ahmrh.serene.ui.screen.main.event

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.theme.SereneTheme


// ill try out later lemme put it into practice screen
@Composable
fun EventScreen(
    navController: NavHostController = rememberNavController(),

){

}

//@Composable
//fun DailyStreakEvent(
//    navController: NavHostController
//){
//
//}
//
//@Composable
//fun AchievementEvent(
//    navController: NavHostController
//){
//
//}



@Composable
fun EventScreenPreview(){
    SereneTheme{
        EventScreen()
    }
}