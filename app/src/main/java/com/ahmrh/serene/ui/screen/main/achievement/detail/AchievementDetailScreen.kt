package com.ahmrh.serene.ui.screen.main.achievement.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import com.ahmrh.serene.ui.screen.main.activity.practice.ErrorContent
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementDetailScreen(
    navController: NavHostController = rememberNavController(),
    achievementId: String = "null",
    viewModel: AchievementDetailViewModel = hiltViewModel()
) {
    viewModel.getAchievementDetail(achievementId)

    val achievementUiState = viewModel.achievementUiState.collectAsState().value

    when(achievementUiState){
        is UiState.Loading -> {
            LoadingContent()
        }
        is UiState.Error -> {
            Text("Error ${achievementUiState.errorMessage}")
        }
        is UiState.Success -> {
            val achievement = achievementUiState.data

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {}, navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.serene_icon_close
                                    ),
                                    contentDescription = null,
                                )

                            }
                        },
                        actions = {

                            IconButton(onClick = {}) {

                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.serene_icon_share
                                    ),
                                    contentDescription = null,
                                )

                            }
                        })
                }
            ) {
                Column(
                    Modifier
                        .padding(it)
                        .padding(horizontal = 36.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){

                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(achievement.imageUri)
                            .build(),
                        contentDescription = "Supporting Image",
                        modifier =
                        Modifier.size(200.dp),
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

                    Spacer(modifier = Modifier.height(32.dp))

                    Text("${achievement.name}", style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedFilterChip(
                        selected = false,
                        onClick = {  },
                        label = {
                            Text("Dec 19, 2023", style = MaterialTheme.typography.titleSmall)
                        })

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("${achievement.description}",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center)
                }

            }

        }

    }

}

@Preview(name= "Light Mode", showBackground = true)
@Preview(name= "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AchievementDetailScreenPreview(){
    SereneTheme {
        AchievementDetailScreen(
        )
    }

}