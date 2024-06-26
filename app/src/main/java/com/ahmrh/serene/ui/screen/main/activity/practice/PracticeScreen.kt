package com.ahmrh.serene.ui.screen.main.activity.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.enums.Sentiment
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.ui.component.button.SentimentButton
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun PracticeScreen(

    navController: NavHostController = rememberNavController(),
    activityId: String? = null,
    viewModel: PracticeViewModel = hiltViewModel(),
) {
    viewModel.getActivityDetail(activityId!!)

    var openFeedback by remember { mutableStateOf(false) }


    val navigateToHome: () -> Unit = {
        navController.navigate(Destination.Serene.Home.route) {
            popUpTo(
                Destination.Serene.route
            ) {
                inclusive =
                    true
            }
        }
    }

    val navigateToEvent = {
        navController.navigate(Destination.Serene.Event.route) {
            popUpTo(Destination.Serene.Home.route)
        }
    }

    viewModel.eventStateLiveData.observe(LocalLifecycleOwner.current) {
        when {
            it -> {
                navigateToEvent()
            }
        }
    }

    when {
        openFeedback -> {
            FeedbackContent(
                onPracticeDone = viewModel::savePracticeHistory
            )

        }

        else -> {

            val activityState =
                viewModel.activityDetailUiState.collectAsState()

            when (activityState.value) {
                is UiState.Success -> {
                    val activity =
                        (activityState.value as UiState.Success<SelfCareActivity>).data
                    PracticeContent(
                        activity = activity,
                        onPracticeDone = {
                            openFeedback = true
                        },
                        navigateToHome = navigateToHome,
                    )
                }

                is UiState.Error -> {
                    ErrorContent()
                }

                is UiState.Loading -> {
                    LoadingContent()
                }
            }
        }
    }

}

@Composable
fun LoadingContent() {
    Surface {

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

}

@Composable
fun ErrorContent() {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContent(
    onPracticeDone: () -> Unit = {},
    activity: SelfCareActivity,
    navigateToHome: () -> Unit
) {


    val openAlertDialog = remember { mutableStateOf(false) }

    when {
        openAlertDialog.value -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.5f)),

                ) {
                SereneDialog(
                    onDismiss = {
                        openAlertDialog.value = false
                    },
                    onConfirm = {
                        openAlertDialog.value = false
                        onPracticeDone()
                    },
                    dialogTitle = "Hey,",
                    dialogText = "Are you sure you already finish this Activity? It's good to follow through general how to or " +
                            "your improvisation to gain the benefit.",
                    confirmText = "Yeah",
                    dismissText = "Nah, please take me back",
                    icon = Icons.Default.Info

                )
            }
        }
    }

    Box {

        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = navigateToHome) {
                                Icon(
                                    painterResource(
                                        id = R.drawable.serene_icon_close
                                    ),
                                    contentDescription = null
                                )
                            }
                        },
                        actions = {
//                        IconButton(onClick = {}) {
//                            Icon(
//                                painterResource(
//                                    id = R.drawable.serene_icon_more_vert
//                                ),
//                                contentDescription = null
//                            )
//                        }
                        },
                    )

                }
            }
        ) {
            Surface(
                modifier = Modifier.padding(it),
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .weight(1f, fill = false),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "You are currently practicing,",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "${activity.name}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                "${activity.category} Self-care",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Image(
                            painterResource(
                                id = CategoryUtils.getCategory(
                                    activity.category!!
                                ).imageResource
                            ),

                            contentDescription = null,
                            Modifier.size(255.dp),
                            contentScale = ContentScale.Crop
                        )


                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                8.dp
                            ),
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp, vertical = 16.dp
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                "General How to:",
                                style = MaterialTheme.typography.titleMedium
                            )

                            val paragraphStyle = ParagraphStyle(
                                textIndent = TextIndent(restLine = 12.sp)
                            )
                            Text(
                                buildAnnotatedString {
                                    activity.guide?.forEach {
                                        withStyle(style = paragraphStyle) {
                                            append(Typography.bullet)
                                            append("\t\t")
                                            append(it)
                                        }
                                    }
                                },

                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Justify
                            )
                        }


                    }


                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "This is simply a general guide, adapt it as needed within the given context.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            onClick = {
                                openAlertDialog.value = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Done")
                        }
                    }

                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackContent(
    onPracticeDone: (
        Sentiment
    ) -> Unit = {}
) {
    var selectedSentiment by remember {
        mutableStateOf(
            Sentiment.NEUTRAL
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(title = {})

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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .weight(weight = 1f, fill = false),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "You've Practiced an Activity",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "How would you rate this Self-care Activity?"
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        val sentiments = listOf(
                            Sentiment.VERY_DISSATISFIED,
                            Sentiment.DISSATISFIED,
                            Sentiment.NEUTRAL,
                            Sentiment.SATISFIED,
                            Sentiment.VERY_SATISFIED,
                        )

                        sentiments.forEach { sentiment ->
                            SentimentButton(
                                modifier = Modifier.size(56.dp),
                                onClick = {
                                    selectedSentiment = sentiment
                                },
                                iconResource = sentiment.iconResource,
                                text = sentiment.stringValue,
                                selected = selectedSentiment == sentiment,
                            )
                        }

                    }


                }

                Button(
                    onClick = {
                        onPracticeDone(selectedSentiment)
                    },
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
@Preview(showBackground = true)
@Composable
fun PracticePreview() {
    SereneTheme {
        PracticeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PracticeContentPreview(){
    val activity = SelfCareActivity("", null)
    SereneTheme{
        PracticeContent(
            activity = activity,
            navigateToHome = {}
        )

    }
}
