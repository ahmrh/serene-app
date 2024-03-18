package com.ahmrh.serene.ui.screen.main.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.AchievementItem
import com.ahmrh.serene.ui.component.card.StatCard
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val TAG = "ProfileScreen"

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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Destination.Serene.Setting.route){
                            popUpTo(Destination.Auth.route){
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            painterResource(id = R.drawable.serene_icon_setting),
                            contentDescription = null
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
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
        }
    ) {

        Surface(modifier = Modifier.padding(it)) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {

//                val selfCareUIState = viewModel.selfCareStartedUiState.collectAsState()
//
//                when(selfCareUIState.value){
//                    is UiState.Success -> {
//                        val value = (selfCareUIState.value as UiState.Success<Boolean>).data
//
//                        Text("Self care started value : $value")
//
//
//                        Button(onClick = {
//                            viewModel.changeSelfCareStartedValue(!value)
//                        }) {
//                            Text("Change Self Care Value")
//                        }
//                    }
//                    else -> {
//                        Text("Self care started value : loading...")
//                        Button(onClick = {
//                        }, enabled = false
//                        ) {
//                            Text("Change Self Care Value")
//                        }
//                    }
//                }



                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp
                    )
                ) {
                    Box(
                        Modifier
                            .size(90.dp)
                            .border(
                                4.dp,
                                MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(
                                    100.dp
                                )
                            )
                            .clip(
                                RoundedCornerShape(
                                    100.dp
                                )
                            )
                    ) {

                        Image(
                            painterResource(id = R.drawable.serene_placeholder_profile_picture),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()

                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        Text(
                            "John Doe",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "Joined August 2023",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                var state by remember {
                    mutableStateOf(
                        0
                    )
                }
                val titles =
                    listOf("Statistic", "History")
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TabRow(selectedTabIndex = state) {
                        titles.forEachIndexed { index, title ->
                            Tab(
                                selected = state == index,
                                onClick = {
                                    state = index
                                },
                                text = {
                                    Text(
                                        text = title,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                        ,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        when (state) {
                            0 -> {
                                StatisticTab()
                            }

                            1 -> {
                                HistoryTab()

                            }
                        }
                    }


                }
            }


        }

    }

}

@Composable
fun StatisticTab() {

    Column(
        verticalArrangement = Arrangement.spacedBy(
            8.dp
        ),
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp
            ),
        ) {
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_streak,
                value = "181",
                label = "Day Streak",
            )
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_achievement,
                value = "10/365",
                label = "Achievement",
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp
            ),
        ) {

            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_selfcare_icon_environmental,
                value = "Environmental",
                label = "Top Self-care",
            )
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_total,
                value = "181",
                label = "Total Self-care",
            )
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Achievement",
                style = MaterialTheme.typography.titleMedium
            )

            TextButton(onClick = {}) {
                Text("View all")
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(4) {
                AchievementItem(variant = 1)
            }
        }
    }
}


@Composable
fun HistoryTab(){
    Text("This feature is still in development")

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SereneTheme {
        ProfileScreen()
    }
}
