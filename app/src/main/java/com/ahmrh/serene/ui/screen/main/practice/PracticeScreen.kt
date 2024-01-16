package com.ahmrh.serene.ui.screen.main.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(

    navController: NavHostController = rememberNavController()
){
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
                        IconButton(onClick = {}) {
                            Icon(
                                painterResource(id = R.drawable.serene_icon_arrow_back),
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                painterResource(id = R.drawable.serene_icon_more_vert),
                                contentDescription = null
                            )
                        }
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
                    .padding(bottom = 36.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Column {
                        Text("Change of space", style = MaterialTheme.typography.titleLarge)
                        Text("Environmental Self-care", style = MaterialTheme.typography.bodyLarge)
                    }

                    Image(
                        painterResource(id = R.drawable.serene_selfcare_image_environmental),
                        contentDescription = null,
                        Modifier.size(250.dp)
                    )


                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        Text("General How to:", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "- Play with twist ties\n" +
                                    "- murder hooman toes\n" +
                                    "- Flop over paw at your fat belly white cat sleeps on a black shirt\n" +
                                    "- Scratch so owner bleeds.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


                }

                Button(
                    onClick = {

                        navController.navigate(
                            Destination.Home.route){

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done")


                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PracticePreview(){
    SereneTheme {
        PracticeScreen()
    }
}