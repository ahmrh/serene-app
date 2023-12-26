package com.ahmrh.serene.ui.screen.main.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController()
){

}

@Composable
fun ProfileScreenPreview(){
    SereneTheme{
        ProfileScreen()
    }
}
