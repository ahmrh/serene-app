package com.ahmrh.serene.ui.screen.main.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.ahmrh.serene.domain.model.selfcare.SelfCareRecommendation
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.card.ChallengeCard
import com.ahmrh.serene.ui.component.card.RecommendationCard
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val username = viewModel.usernameState.collectAsState().value

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selfCareStartedUiState =
        viewModel.selfCareStartedUiState.collectAsState()
    val startedActivityIdState =
        viewModel.startedActivityIdState.collectAsState()

    val personalizationResultState =
        viewModel.personalizationResultState.collectAsState()

    val firstTimeOpenedState = viewModel.firstTimeOpenedState.collectAsState()

    val recommendationUiState =
        viewModel.recommendationSelfCareState.collectAsState()

    val challengeListUiState = viewModel.challengeListState.collectAsState()

    val personalizationResultDialog = remember { mutableStateOf(false) }

    var openPersonalizationDialog by remember { mutableStateOf(personalizationResultState.value == null) }


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

    val navigateToActivityList =  { categoryId: Int ->
        navController.navigate(
            Destination.Serene.ActivityList.createRoute(
                categoryId = categoryId
            )
        )
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

    val navigateToActivityDetail: (id: String) -> Unit = {
        navController.navigate(
            Destination.Serene.ActivityDetail.createRoute(it)
        )
    }

    val navigateToIntro: () -> Unit = {
        navController.navigate(
            Destination.Serene.Introduction.route
        )
    }

    val activity = LocalContext.current as? Activity

    BackHandler {

        activity?.finish()
    }

    when {
        openPersonalizationDialog -> {

            if(!firstTimeOpenedState.value){

                SereneDialog(
                    onDismiss = {
                        openPersonalizationDialog = false
                    }, onConfirm = {
                        navigateToPersonalization()
                    },
                    dialogTitle = "Hello,",
                    dialogText =  "Welcome to Serene! It seems you don't have personalization yet. Personalize your Self-care?",
                    dismissText = "Nah",
                    confirmText = "Personalize"
                )
            } else {

                SereneDialog(
                    onDismiss = {
                        navigateToIntro()
                    }, onConfirm = {
                       navigateToIntro()
                    },
                    dialogTitle = "Hello,",
                    dialogText =  "Welcome to Serene! It seems it seems it's your first time using this app, let's go through some introduction.",
                    confirmText = "Okay"
                )
            }

        }
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
                        if (personalizationResultState.value != null) {
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
        },
        topBar = {
            TopAppBar(title = {

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ) {

                    Text(
                        "Welcome back, ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        username ?: "Anon",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
                actions = {
//                    Box(
//                        modifier = Modifier.fillMaxHeight(),
//                        contentAlignment = Alignment.Center
//                    ) {
//
//                        IconButton(onClick = {
//                            navigateToIntro()
//                        }) {
//                            Icon(
//                                Icons.Outlined.Info,
//                                contentDescription = null
//                            )
//                        }
//                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                RecommendationSection(
                    navigateToDetail = navigateToActivityDetail,
                    recommendationUiState = recommendationUiState.value
                )


                ChallengesSection(
                    navigateToActivityList = navigateToActivityList,
                    navigateToPersonalization = {
                        navigateToPersonalization()
                    },
                    challengeListState = challengeListUiState.value
                )


//                Text(
//                    "Personalization Result State : ${personalizationResultState.value}"
//                )
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
    navigateToDetail: (id: String) -> Unit = {},
    recommendationUiState: UiState<List<SelfCareRecommendation>?>
) {
    when(recommendationUiState){
        is UiState.Success -> {

            Column {
                Text("Based on your personalization", style = MaterialTheme.typography.titleMedium)
            }
            val recommendations = recommendationUiState.data!!
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recommendations){

                    RecommendationCard(
                        onClick = {
                            navigateToDetail(it.selfCareId!!)
                        },
                        recommendation = it
                    )
                }
            }
        }

        is UiState.Loading -> {

        }
        is UiState.Error -> {
            Text("error: ${recommendationUiState.errorMessage}")
        }
    }


}

@Composable
fun ChallengesSection(
    navigateToActivityList: (Int) -> Unit,
    navigateToPersonalization: () -> Unit ,
    challengeListState: UiState<List<Challenge>>
) {

    Column {
        Text(
            "Today Challenges",
            style = MaterialTheme.typography.titleMedium
        )
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){

        when(challengeListState){
            is UiState.Success -> {
                val challengeList = challengeListState.data

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    challengeList.forEach{

                        ChallengeCard(
                            value = it.progress.toLong(),
                            maxValue = 1,
                            isDone = it.isDone,
                            title = it.title,
                            description = it.description,
                            modifier =
                                when(it.challengeType) {

                                    is ChallengeType.DEFAULT -> {
                                        Modifier
                                    }
                                    is ChallengeType.PERSONALIZATION -> {
                                        Modifier.clickable {
                                            navigateToPersonalization()
                                        }
                                    }
                                    is ChallengeType.PRACTICE -> {
                                        Modifier.clickable {
                                            navigateToActivityList(it.challengeType.category.id)

                                        }
                                    }
                                }
                        )
                    }

                    /* ChallengeCard(

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
                    */

                }
            }
            else -> {
                Text("Generating challenges...")
            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SereneTheme {
        HomeScreen()
    }
}