package com.ahmrh.serene.ui.component.navbar

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme


@Composable
fun SereneNavBar(
    navController: NavController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Activities") },
            label = { Text("Activities") },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.ActivityCategory.route } == true,
            onClick = {
                navController.navigate(
                    Destination.ActivityCategory.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
        )

        if(currentDestination?.hierarchy?.any { it.route == Destination.Home.route } == false){

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = false,
                onClick = {

                    navController.navigate(
                        Destination.Home.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )

        } else {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight(),
            ){
                SereneButton (
                    onClick = {
                        Log.d("Navigation Bar", "I pressed Self-care button!")
                        navController.navigate(
                            Destination.Introduction.createRoute(1))
                    }
                )
            }
        }


        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Profile.route } == true,
            onClick = {
                navController.navigate(
                    Destination.Profile.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
        )

    }
}

@Composable
fun SereneButton(onClick: () -> Unit){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(100),
            )
            .clickable {
                onClick()
            }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.serene_icon_lotus),
            contentDescription = "Serene Button",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(40.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SereneNavBarPreview() {
    SereneTheme {
        SereneNavBar()
    }
}
