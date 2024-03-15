package com.ahmrh.serene.ui.screen.main.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.card.ChallengeCard
import com.ahmrh.serene.ui.component.card.RecommendationCard
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selfCareStartedUiState =
        viewModel.selfCareStartedUiState.collectAsState()
    val startedActivityIdState =
        viewModel.startedActivityIdState.collectAsState()

    val personalizationResultState =
        viewModel.personalizationResultState.collectAsState()

    val personalizationResultDialog = remember { mutableStateOf(false) }

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
    val navigateToPersonalization = {
        navController?.navigate(
            Destination.Serene.Personalization.route
        )
    }

    val navigateToPractice = {
        val startedActivityId = startedActivityIdState.value
        if (startedActivityId != null) {
            navController?.navigate(
                Destination.Serene.Practice.createRoute(
                    startedActivityId
                )
            )
        }
    }

    val navigateToResult: (category: Category) -> Unit = {
        val categoryId = it.id

        navController.navigate(
            Destination.Serene.Result.createRoute(
                categoryId
            )
        )

    }

    val activity = LocalContext.current as? Activity
    BackHandler {

        activity?.finish()
    }


    Scaffold(
        bottomBar = {
            SereneNavBar(

                onActivityNavigation = {
                    navigateToActivities()
                },
                onSereneNavigation = {
                    val selfCareStarted = selfCareStartedUiState.value

                    if (selfCareStarted) navigateToPractice()
                    else {
                        if(personalizationResultState.value != null) {
                            personalizationResultDialog.value = true
                        } else {
                            navigateToPersonalization()
                        }
                    }
                },
                onHomeNavigation = {
                    navigateToHome()
                },
                onProfileNavigation = {
                    navigateToProfile()
                },

                currentDestination = currentDestination,
                selfCareStarted = selfCareStartedUiState.value

            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
        ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Welcome back, ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        "Mr. Hyobanshi",
                        style = MaterialTheme.typography.titleMedium
                    )
                }


                RecommendationSection(navigateToDetail = { activityId ->

                    navController?.navigate(
                        Destination.Serene.ActivityDetail.createRoute(activityId)
                    ) {
                        popUpTo(
                            navController.graph.findStartDestination().id
                        ) {
                            saveState = true
                        }
                    }
                })

                ChallengesSection()


                Text("Personalization Result State : ${personalizationResultState.value}")
            }

        }
    }

    when {
        personalizationResultDialog.value -> {
            SereneDialog(
                onDismiss = {
                    personalizationResultDialog.value = false
                    navigateToResult(personalizationResultState.value!!)
                },
                onConfirm = {
                    personalizationResultDialog.value = false
                    navigateToPersonalization()
                },
                dialogTitle = "Hey, ",
                dialogText = "It seems you have done personalization before, would you like to start a new personalization again?",
                confirmText = "Yep",
                dismissText = "See my previous result",
                icon = Icons.Default.Info

            )
        }
    }

}

@Composable
fun RecommendationSection(
    navigateToDetail: (id: String) -> Unit = {}
) {

    Column {
        Text("For you", style = MaterialTheme.typography.titleMedium)
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            RecommendationCard(
                onClick = {
                    navigateToDetail("activity id")

                }
            )
        }
    }
}

@Composable
fun ChallengesSection() {

    Column {
        Text(
            "Today Challenges",
            style = MaterialTheme.typography.titleMedium
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChallengeCard(
            value = 1,
            maxValue = 5,
            title = "For your mental",
            description = "Do 5 mental Self-care today"
        )
        ChallengeCard(

            value = 4,
            maxValue = 10,
            title = "Beat the laziness",
            description = "Do 10 Physical Self-care today"
        )
        ChallengeCard(

            value = 2,
            maxValue = 10,
            title = "Mind your emotion",
            description = "Do 10 Emotional Self-care today"
        )

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SereneTheme {
        HomeScreen()
    }
}