package com.ahmrh.serene.ui.screen.main.activity

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.card.ActivityCard
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityCategoryScreen(
    navController: NavHostController = rememberNavController(),
) {
    val TAG = "ActivityCategoryScreen"

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigateToActivities = {
        navController?.navigate(
            Destination.Serene.ActivityCategory.route
        ) {
            popUpTo(
                navController.graph.findStartDestination().id
            ) {
                saveState = true
            }
        }
    }
    val navigateToProfile = {
        navController?.navigate(
            Destination.Serene.Profile.route
        ) {
            popUpTo(
                navController.graph.findStartDestination().id
            ) {
                saveState = true
            }
        }
    }
    val navigateToHome = {
        navController?.navigate(
            Destination.Serene.Home.route
        ) {
            popUpTo(
                navController.graph.findStartDestination().id
            ) {
                saveState = true
            }
        }
    }

    Scaffold(
        bottomBar = {
            SereneNavBar(
                onActivityNavigation = {
                    Log.d(TAG, "Navigate to activity")
                    navigateToActivities()
                },
                onHomeNavigation = {
                    Log.d(TAG, "Navigate to home")
                    navigateToHome()
                },
                onProfileNavigation = {
                    Log.d(TAG, "Navigate to profile")
                    navigateToProfile()
                },

                currentDestination = currentDestination,
            )
        },
        topBar = {
            TopAppBar(
                title = { Text("Activities") },
//                actions = {
//                    IconButton(
//                        onClick = { }) {
//
//                        Icon(
//                            painterResource(
//                                id = R.drawable.serene_icon_bookmarks_collection
//                            ),
//                            contentDescription = null,
//                            modifier = Modifier.size(
//                                24.dp
//                            )
//                        )
//                    }
//                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                }

                LazyColumn{
                    items(7) { index ->
                        ActivityCard(
                            categoryId = index + 1,
                            onClick = {
                                navController.navigate(
                                    Destination.Serene.ActivityList.createRoute(
                                        categoryId = index + 1
                                    )
                                )
                            },
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ActivityCategoryScreenPreview() {
    SereneTheme {
        ActivityCategoryScreen()
    }
}