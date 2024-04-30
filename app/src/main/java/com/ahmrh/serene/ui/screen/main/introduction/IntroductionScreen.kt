package com.ahmrh.serene.ui.screen.main.introduction

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme
import kotlinx.coroutines.delay

@Composable
fun IntroductionScreen(
    navController: NavController = rememberNavController(),
    viewModel: IntroductionViewModel = hiltViewModel()
) {
    val index = viewModel.introIndexStateFlow.collectAsState().value

    val interactionSource = remember { MutableInteractionSource() }

    var isVisible by remember { mutableStateOf(false) }

    BackHandler{
        if(index == 0)
            navController?.navigate(
                Destination.Serene.Home.route){
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        else
            viewModel.prevIndex()

    }


    LaunchedEffect(key1 = null){
        isVisible = false

        delay(100)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible, enter = fadeIn()
    ) {

        Scaffold(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    if(index < 9)
                        viewModel.nextIndex()
                    else{
                        viewModel.changeFirstTimeOpenedValue(false)
                        navController.navigate(Destination.Serene.Personalization.route)

                    }

                }
        ) {

            Column(
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                modifier = androidx.compose.ui.Modifier
                    .height(580.dp)
                    .fillMaxWidth()
                    .padding(it),
            ) {
                if(index <= 2){

                    Text(
                        text =  stringResource(
                            id =
                                if(index == 1) com.ahmrh.serene.R.string.serene_introduction_1
                                else com.ahmrh.serene.R.string.serene_introduction_2
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(278.dp)
                    )
                }
                else {
                    SelfcareIntroSection(
                        index - 2
                    )
                }


            }
        }
    }


}

@Composable
fun SelfcareIntroSection(
    selfCareId: Int,
) {
    val category =
        CategoryUtils.getCategory(selfCareId)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {

            Text(
                "${category.stringValue} Self-care",
                style = MaterialTheme.typography.titleLarge
            )

            Image(
                painterResource(
                    id = category.imageResource
                ),
                contentDescription = null,
                modifier = Modifier.width(300.dp).padding(bottom = 8.dp)
            )

            Text(
                stringResource(
                    id = category.introductionDescriptionResource
                ),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center

            )
        }
    }



}


@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun IntroductionScreenPreview() {
    SereneTheme {
        IntroductionScreen()
    }
}