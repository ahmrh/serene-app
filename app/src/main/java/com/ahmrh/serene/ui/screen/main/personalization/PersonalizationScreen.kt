package com.ahmrh.serene.ui.screen.main.personalization

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.screen.main.personalization.content.BaseQuestionContent
import com.ahmrh.serene.ui.screen.main.personalization.content.QuestionContent
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun PersonalizationScreen(

    navController: NavHostController = rememberNavController(),
    viewModel: PersonalizationViewModel = hiltViewModel(),
) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val personalizationType =
        viewModel.personalizationTypeState.collectAsState().value

    val navigateToHome = {
        navController?.navigate(
            Destination.Home.route
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }
    val navigateToActivities: (category: Category) -> Unit = {
        navController?.navigate(
            Destination.ActivityList.createRoute(categoryId = it.id)
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    val navigateToResult: (categoryId: Int) -> Unit = {
        navController.navigate(
            Destination.Result.createRoute(categoryId = it)
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    BackHandler {
        openAlertDialog = true
    }

    when (personalizationType) {
        PersonalizationType.BASE -> {

            BaseQuestionContent(
                onNext = {
                    viewModel.changeToQuestionType()
                    viewModel.fetchQuestionList()
                },
                viewModel = viewModel,
                onBackHandler = {
                    openAlertDialog = true
                }
            )

        }

        PersonalizationType.QUESTION -> {


            val questionListStateValue =
                viewModel.questionListState.collectAsState().value

            when (questionListStateValue) {
                is UiState.Success -> {
                    val questionList = questionListStateValue.data

                    QuestionContent(
                        navigateToResult = navigateToResult,
                        questionList = questionList,
                        viewModel = viewModel,
                    )

                }

                is UiState.Loading -> {
                    LoadingContent()

                }

                is UiState.Error -> {
                    Text(questionListStateValue.errorMessage)

                }
            }

        }

//        PersonalizationType.RESULT -> {
//            ResultContent(
//                viewModel = viewModel,
//                onClose = { navigateToHome() },
//                navigateToHome = { navigateToHome() },
//                navigateToActivities = navigateToActivities
//            )
//        }

    }


//    IntroductionScreen(navController)

    when {
        openAlertDialog -> {
            SereneDialog(
                onDismiss = {
                    openAlertDialog = false
                }, onConfirm = {
                    openAlertDialog = false
                    navigateToHome()
                },
                dialogTitle = "Um,",
                dialogText = "Are you sure you want to go stop your personalization? Once you return you need to start it all over",
                confirmText = "Yes",
                dismissText = "No, take me back",
                icon = Icons.Default.Info
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonalizationContent(
    viewModel: PersonalizationViewModel
) {
    Scaffold {
        Surface {


        }
    }


}

@Composable
fun PersonalizationScreenPreview() {
    SereneTheme {
        PersonalizationScreen()
    }
}