package com.ahmrh.serene.ui.screen.main.achievement.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.AchievementItem
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementListScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AchievementListViewModel = hiltViewModel()
) {

    val achievementListState = viewModel.achievementListUiState.collectAsState().value

    when(achievementListState){
        is UiState.Success -> {

            val achievementList = achievementListState.data

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {}, navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {

                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.serene_icon_arrow_back
                                    ),
                                    contentDescription = null,
                                )

                            }
                        })
                }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(
                        minSize = 150.dp
                    ), modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(achievementList){ achievement ->
                        AchievementListItem(
                            onClick = {
                                Log.d("AchievementListScreen", "AchievementId clicked: ${achievement.id}")
                                navController.navigate(Destination.Serene.AchievementDetail.createRoute(achievement.id!!))
                            },
                            achievement = achievement
                        )
                    }

                }
            }
        }

        is UiState.Error -> {
            Text("error")
        }
        is UiState.Loading -> {
            LoadingContent()
        }
    }

}

@Composable
fun AchievementListItem(
    achievement: Achievement?,
    onClick: () -> Unit = {}
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            8.dp
        ),
        modifier = Modifier
            .clickable{
                onClick()
            }
            .padding(
                bottom = 8.dp
            )
    ) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(achievement?.imageUri)
                .build(),
            contentDescription = "Supporting Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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
        Text(
            text = "${achievement?.name}",
            style = MaterialTheme.typography.titleSmall
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AchievementListItemPreview(){
    SereneTheme(){
        AchievementListItem(achievement = null)
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementListScreenPreview() {
    SereneTheme {
        AchievementListScreen(
        )
    }
}