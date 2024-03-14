package com.ahmrh.serene.ui.screen.main.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ResultScreen(
    navController: NavHostController,
    categoryId: Int?,
){
    val navigateToHome = {
        navController?.navigate(
            Destination.Home.route){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    val navigateToActivities: (categoryId: Int) -> Unit = {
        navController?.navigate(
            Destination.ActivityList.createRoute(categoryId = it)
        ){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    val onShare = {

    }

    val onBack = {
        navigateToHome()
    }

    BackHandler {
        navigateToHome()
    }

    val category = CategoryUtils.getCategory(categoryId ?: -1)

    Scaffold(
        topBar = {
            Row(
                Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                IconButton(
                    onClick = { onBack() }
                ){
                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_close
                        ),
                        contentDescription = null
                    )
                }

                IconButton(onClick = onShare) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_share
                        ),
                        contentDescription = null
                    )
                }
            }
        }

    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 18.dp,
                        bottom = 36.dp
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = category.imageResource),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Based on your result, you might need",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "${category.stringValue} Self-care",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    Spacer(Modifier.height(8.dp))


                    Text(
                        stringResource(
                            id = category.resultDescriptionResource
                        ), style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "This result is based on how well you are on different self-care category",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            navigateToActivities(category.id)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Go to Activities")


                    }
                }

            }

        }

    }

}

@Composable
fun ResultScreenPreview(){
    SereneTheme{
//        ResultScreen()
    }
}