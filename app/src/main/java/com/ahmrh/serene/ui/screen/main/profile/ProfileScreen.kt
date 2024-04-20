package com.ahmrh.serene.ui.screen.main.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.HistoryItem
import com.ahmrh.serene.ui.component.card.StatCard
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ProfileViewModel = hiltViewModel()
) {

//    val achievementList = viewModel.achievementListUiState.collectAsState().value
//    val historyList = viewModel.historyListUiState.collectAsState().value
//
//    val dayStreak = viewModel.dailyStreakUiState.collectAsState().value
//    val totalAchievement = viewModel.totalAchievementUiState.collectAsState().value
//    val topSelfCare = viewModel.topSelfCareUiState.collectAsState().value
//    val totalSelfCare = viewModel.totalSelfCareUiState.collectAsState().value
    
    val profileDataUiState = viewModel.profileDataUiState.collectAsState().value

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

    val navigateToSetting = {
        navController.navigate(Destination.Serene.Setting.route)
    }

    val navigateToAchievementList = {
        navController.navigate(Destination.Serene.AchievementList.route)
    }

    val navigateToAchievement = { id : String ->
        navController.navigate(Destination.Serene.AchievementDetail.createRoute(id))
    }

    val horizontalPaddingValues = 24.dp
    
    when(profileDataUiState){
        is UiState.Loading -> {
            LoadingContent()
        }

        is UiState.Error -> {
            Text("Error")
        }
        is UiState.Success -> {
            
            val profileData = profileDataUiState.data

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
                            IconButton(onClick = navigateToSetting) {
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
                    ) {


                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = horizontalPaddingValues),
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
                                    "${profileData.username}",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    "Joined August 2023",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }

                        var state by remember {
                            mutableIntStateOf(
                                0
                            )
                        }
                        val titles =
                            listOf("Statistic", "History")
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TabRow(selectedTabIndex = state, modifier = Modifier
                                .padding(horizontal = horizontalPaddingValues)) {
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
                                        StatisticTab(
                                            achievementList = profileData.achievementList,
                                            dayStreak = profileData.dayStreak,
                                            totalAchievement = profileData.totalAchievement,
                                            topSelfCare = profileData.topSelfCare,
                                            totalSelfCare = profileData.totalSelfCare,
                                            navigateToAchievementList = navigateToAchievementList,
                                            navigateToAchievement = navigateToAchievement,
                                            modifier = Modifier.padding(horizontal = horizontalPaddingValues)
                                        )
                                    }

                                    1 -> {
                                        HistoryTab(
                                            historyList = profileData.historyList
                                        )

                                    }
                                }
                            }


                        }
                    }


                }

            }
        }
    }


}

@Composable
fun StatisticTab(
    achievementList: List<Achievement>,
    dayStreak: Int,
    totalAchievement: Int,
    topSelfCare: String?,
    totalSelfCare: Int,
    navigateToAchievementList: () -> Unit,
    navigateToAchievement: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(
            8.dp
        ),
        modifier = modifier.fillMaxWidth().padding(top = 8.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp
            ),
        ) {
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_streak,
                value = "$dayStreak",
                label = "Day Streak",
            )
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_achievement,
                value = "$totalAchievement",
                label = "Achievement",
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp
            ),
        ) {

            val topSelfCareCategory = CategoryUtils.getCategory(topSelfCare ?: "Emotional")

            StatCard(
                Modifier.weight(1f),
                topSelfCareCategory.iconResource,
                value = topSelfCare ?: "None",
                label = "Top Self-care",
            )
            StatCard(
                Modifier.weight(1f),
                R.drawable.serene_stat_icon_total,
                value = "$totalSelfCare",
                label = "Total Self-care",
            )
        }
    }
//    Spacer(modifier = Modifier.height(16.dp))

    Column (
        modifier = modifier

    ){

        if(achievementList.isNotEmpty()){


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Achievement",
                    style = MaterialTheme.typography.titleMedium
                )

                if(achievementList.size > 3){

                    TextButton(
                        onClick = {
                            navigateToAchievementList()
                        }
                    ) {
                        Text("View all")
                    }
                }

            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                items(achievementList){

                    AchievementItem(achievement = it, onClick = {
                        navigateToAchievement(it.id!!)
                    })
                }
            }
        }
//        else {
//            Box(
//                modifier = Modifier.padding(16.dp).fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//
//                Text("Practice Activity to Unlock Achievement")
//            }
//        }
    }
}


@Composable
fun AchievementItem(
    achievement: Achievement,
    onClick: () -> Unit = {}
) {

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(achievement?.imageUri)
            .build(),
        contentDescription = "Supporting Image",
        Modifier
            .wrapContentSize(
                unbounded = true,
                align = Alignment.BottomCenter
            )
            .size(140.dp)
            .clickable { onClick() },
        contentScale = ContentScale.Crop,
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
}


@Composable
fun HistoryTab(
    historyList: List<SelfCareHistory>
){
//    Text("This feature is still in development")

    LazyColumn (
        modifier = Modifier.padding(horizontal = 8.dp)
    ){
        items(historyList){
            HistoryItem(selfCareHistory = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SereneTheme {
        ProfileScreen()
    }
}
