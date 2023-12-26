package com.ahmrh.serene.ui.screen.main.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Profile")
            })
        }
    ){
        Surface(modifier = Modifier.padding(it)){

        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    SereneTheme{
        ProfileScreen()
    }
}
