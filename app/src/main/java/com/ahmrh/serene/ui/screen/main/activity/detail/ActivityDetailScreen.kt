package com.ahmrh.serene.ui.screen.main.activity.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ahmrh.serene.R
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.CircularLoading
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.theme.SereneTheme
import kotlin.text.Typography.bullet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetailScreen(

    navController: NavHostController = rememberNavController(),
    activityId: String? = null,
    viewModel: ActivityDetailViewModel = hiltViewModel()
) {

    viewModel.getActivityDetail(activityId ?: "null")

    val activityState = viewModel.activityDetailUiState.collectAsState().value
    val practiceEnabled =
        viewModel.enabledPracticeButtonUiState.collectAsState().value

    val startedActivityIdState = viewModel.startedActivityIdState.collectAsState()


    when (activityState) {
        is UiState.Success -> {
            val activity = activityState.data

            val startedActivityId = startedActivityIdState.value

            ActivityDetailContent(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToPractice = {
                    viewModel.changeStartedActivityIdValue(activity.id)
                    navController.navigate(
                        Destination.Serene.Practice.createRoute(activity.id)
                    ){
                        popUpTo(Destination.Serene.Home.route) {
                            saveState = true
                        }
                    }
                },
                activity = activity,
                practiceEnabled = if(startedActivityId == activityId) true else practiceEnabled
            )

        }

        is UiState.Loading -> {
            CircularLoading()
        }

        is UiState.Error -> {
            Text("Error ")

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetailContent(
    navigateBack: () -> Unit = {},
    navigateToPractice: () -> Unit = {},
    activity: SelfCareActivity,
    practiceEnabled: Boolean,
) {

    val category = CategoryUtils.getCategory(activity.category ?: "")

    val otherSelfCareStartedAlertDialog = remember { mutableStateOf(false) }

    val startingSelfCareAlertDialog = remember { mutableStateOf(false) }

    when {
        otherSelfCareStartedAlertDialog.value -> {
            SereneDialog(
                onDismiss = {
                    otherSelfCareStartedAlertDialog.value = false
                },
                onConfirm = {
                    otherSelfCareStartedAlertDialog.value = false
                    navigateToPractice()

                },
                dialogTitle = "Oops",
                dialogText = "It seems another Self-care activity already started, " +
                        "do you want to practice this activity instead?",
                confirmText = "I wanted to try this",
                dismissText = "Nope",
                icon = Icons.Default.Info

            )
        }
    }

    when {
        startingSelfCareAlertDialog.value -> {
            SereneDialog(
                onDismiss = {
                    startingSelfCareAlertDialog.value = false
                },
                onConfirm = {
                    startingSelfCareAlertDialog.value = false
                    navigateToPractice()

                },
                dialogTitle = "Hey, ",
                dialogText = "Do you want to start practicing this Self-care Activity today? You had to finish one first before starting another",
                confirmText = "Yup",
                dismissText = "Nah, please take me back",
                icon = Icons.Default.Info

            )
        }
    }

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
                        IconButton(onClick = navigateBack) {
                            Icon(
                                painterResource(
                                    id = R.drawable.serene_icon_arrow_back
                                ),
                                contentDescription = null
                            )
                        }
                    },
//                    actions = {
//
//                        IconButton(
//                            onClick = {},
//                            modifier = Modifier
//                        ) {
//                            Icon(
//                                painterResource(
//                                    id = R.drawable.serene_icon_bookmark
//                                ),
//                                contentDescription = null
//                            )
//                        }
//
//                        IconButton(onClick = {}) {
//                            Icon(
//                                painterResource(
//                                    id = R.drawable.serene_icon_more_vert
//                                ),
//                                contentDescription = null
//                            )
//                        }
//                    },
                )


//                Box(
//                    modifier = Modifier
//                        .align(Alignment.TopCenter),
//                ) {
//                    Image(
//                        painterResource(id = R.drawable.serene_selfcare_image_environmental),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .wrapContentSize(unbounded = true, align = Alignment.TopCenter)
//                            .size(180.dp)
//                    )
//                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
        ) {



            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 20.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    ),
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                ) {
                    Icon(
                        painterResource(
                            id = category.iconResource
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )

                    Column(
                    ) {
                        Text(
                            "${activity.name}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "${activity.category} Self-care",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(activity.imageUri)
                            .build(),
                        contentDescription = "Supporting Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(165.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
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

//                    CoilImage(
//                        imageModel = { activity.imageUri },
//                        imageOptions = ImageOptions(
//                            contentScale = ContentScale.Crop,
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(165.dp)
//                            .clip(RoundedCornerShape(8.dp)),
//                        component = rememberImageComponent {
//                            +ShimmerPlugin(
//                                Shimmer.Fade(
//                                    baseColor = MaterialTheme.colorScheme.surfaceContainerLow,
//                                    highlightColor = MaterialTheme.colorScheme.onSurface,
//                                ),
//                            )
//                            +CrossfadePlugin(
//                                duration = 550
//                            )
//
//                        },
//                        failure = {
//                            Text(text = "image request failed.")
//                        }
//                    )

                    Text(
                        "${activity.description}",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )


                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        Text(
                            "Benefit",
                            style = MaterialTheme.typography.titleMedium
                        )

                        val paragraphStyle = ParagraphStyle(
                            textIndent = TextIndent(restLine = 12.sp)
                        )
                        Text(
                            buildAnnotatedString {
                                activity.benefit?.forEach {
                                    withStyle(style = paragraphStyle) {
                                        append(bullet)
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



                Button(
                    onClick = {
                        if (practiceEnabled) {
                            startingSelfCareAlertDialog.value = true
                        }
                        else {
                            otherSelfCareStartedAlertDialog.value = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = if (!practiceEnabled) ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface.copy(
                            0.12f
                        ),
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ) else ButtonDefaults.buttonColors()
                ) {
                    Text(
                        if (practiceEnabled) "Practice Self-care" else "You still practiced other Self-care"
                    )
                }

            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ActivityDetailScreenPreview() {
    SereneTheme {
//        ActivityDetailContent(activity = SelfCareActivity("id"))
    }
}