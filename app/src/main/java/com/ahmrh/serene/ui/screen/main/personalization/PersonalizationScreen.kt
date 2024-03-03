package com.ahmrh.serene.ui.screen.main.personalization

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.screen.main.introduction.IntroductionScreen
import com.ahmrh.serene.ui.screen.main.personalization.question.BaseQuestionScreen
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun PersonalizationScreen(

    navController: NavHostController = rememberNavController(),
    viewModel: PersonalizationViewModel = hiltViewModel(),
){
    val personalizationType = viewModel.personalizationTypeState.value

    val navigateToHome = {
        navController?.navigate(
            Destination.Home.route){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }
    BackHandler {
        navigateToHome()
    }

    BaseQuestionScreen(
        onNext = {


        },
        viewModel = viewModel
    )








//    IntroductionScreen(navController)


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonalizationContent(
    viewModel: PersonalizationViewModel
){
    Scaffold {
        Surface{


        }
    }



}

@Composable
fun PersonalizationScreenPreview(){
    SereneTheme{
        PersonalizationScreen()
    }
}