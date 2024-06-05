package com.ahmrh.serene.ui.screen.main.event

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.gamification.DailyStreak
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.DailyStreakFlame
import com.ahmrh.serene.ui.component.DailyStreakFlameVariant
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme
import java.util.Calendar

@Composable
fun EventScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: EventViewModel = hiltViewModel()
) {
    Surface {

        val navigateToHome = {
            navController.navigate(
                Destination.Serene.Home.route
            ) {
                popUpTo(
                    navController.graph.findStartDestination().id
                ) {
                    saveState = true
                }
            }
        }


        LaunchedEffect(key1 = null) {

            if(!viewModel.achievementEventOpen.value && !viewModel.dailyStreakEventOpen.value){
                navigateToHome()
            }
        }

        val onEventDismissed = {
            if(!viewModel.achievementEventOpen.value && !viewModel.dailyStreakEventOpen.value){
                navigateToHome()
            }
        }

        when {
            viewModel.dailyStreakEventOpen.value -> {

                val dailyStreakState =
                    viewModel.dailyStreakEventState

                DailyStreakEvent(
                    onDismiss = {
                        viewModel.dailyStreakEventOpen.value = false
                        onEventDismissed()

                    },
                    dailyStreak = dailyStreakState.value,
                )
            }

        }

        when {

            viewModel.achievementEventOpen.value -> {
                val achievementUiState =
                    viewModel.achievementEventUiState.collectAsState().value

                AchievementEvent(
                    onDismiss = {
                        viewModel.achievementEventOpen.value = false
                        onEventDismissed()
                    },
                    achievementUiState = achievementUiState,
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyStreakEvent(
    dailyStreak: DailyStreak?,
    onDismiss: () -> Unit,
) {
    BackHandler {
        onDismiss()
    }

    dailyStreak?.let {

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {},
                    actions = {

//                        IconButton(onClick = {}) {
//
//                            Icon(
//                                painter = painterResource(
//                                    id = R.drawable.serene_icon_share
//                                ),
//                                contentDescription = null,
//                            )
//
//                        }
                    })
            }
        ) {
            Surface(
                modifier = Modifier.padding(it)
            ) {

                Column(
                    Modifier

                        .padding(top = 48.dp, bottom = 20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {

                    Column(
                        modifier = Modifier
                            .weight(weight = 1f, fill = false),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(32.dp))
                        DailyStreakFlame(
                            variant = DailyStreakFlameVariant.LARGE,
                            textString = "${dailyStreak.count}"
                        )
//                        Spacer(modifier = Modifier.height(24.dp))


//                        val cal = Calendar.getInstance()
//                        cal.time = dailyStreak.date
//                        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
//
//                        val dayList =
//                            listOf("S", "M", "T", "W", "T", "F", "S")
//
//                        val dayStreak = dailyStreak.count
//
//                        Row {
//                            val todayIndex = dayOfWeek - 1
//
//                            val streakStartIndex =
//                            dayList.forEachIndexed { index, day ->
//
//                                DailyStreakFlame(
//                                    variant = DailyStreakFlameVariant.SMALL,
//                                    textString = day,
//                                    enabled = index < dayOfWeek
//                                )
//
//                            }
//
//                        }
//
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            "${dailyStreak.count} Day Streak",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            "Congratulation on your day streak! \nKeep practicing self-care activity everyday to build up your streak",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                horizontal = 32.dp
                            )
                        )
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    ) {
                        Text("Continue")


                    }
                }
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementEvent(
    achievementUiState: UiState<Achievement>,
    onDismiss: () -> Unit
) {
    BackHandler {
        onDismiss()
    }
    when (achievementUiState) {
        is UiState.Loading -> LoadingContent()
        is UiState.Error -> {}
        is UiState.Success -> {
            val achievement = achievementUiState.data

            Scaffold(
                topBar = {

                    TopAppBar(
                        title = {},
                        actions = {

//                            IconButton(onClick = {}) {
//
//                                Icon(
//                                    painter = painterResource(
//                                        id = R.drawable.serene_icon_share
//                                    ),
//                                    contentDescription = null,
//                                )
//
//                            }
                        })
                }
            ) {
                Surface(
                    modifier = Modifier
                        .padding(it)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 48.dp, bottom = 20.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(
                            Modifier
                                .weight(weight = 1f, fill = false)
                                .padding(horizontal = 24.dp),

                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            Spacer(modifier = Modifier.height(32.dp))
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(
                                    LocalContext.current
                                )
                                    .data(achievement?.imageUri)
                                    .build(),
                                contentDescription = "Supporting Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(250.dp)
                            ) {
                                val state = painter.state
                                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .background(
                                                MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                    8.dp
                                                )
                                            )
                                    )
                                } else {
                                    SubcomposeAsyncImageContent()
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(
                                "${achievement?.name}",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            val date = Calendar.getInstance().time
                            ElevatedFilterChip(
                                selected = false,
                                onClick = { },
                                label = {
                                    Text(
                                        DateUtils.formatSimpleDate(date),
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                })

                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                "${achievement?.description}",
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }

                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
                            Text("Continue")


                        }
                    }
                }

            }
        }
    }

}


@Composable
fun EventScreenPreview() {
    SereneTheme {
//        EventScreen()
    }
}


@Preview(showBackground = true)
@Composable
fun DailyStreakPreview() {
    SereneTheme {
//        DailyStreakEvent(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementPreview() {
    SereneTheme {
//        AchievementEvent()
    }
}