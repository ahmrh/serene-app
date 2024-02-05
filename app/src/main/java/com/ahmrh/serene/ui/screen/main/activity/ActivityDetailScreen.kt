package com.ahmrh.serene.ui.screen.main.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetailScreen(

    navController: NavHostController = rememberNavController()
) {
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
                                painterResource(
                                    id = R.drawable.serene_icon_arrow_back
                                ),
                                contentDescription = null
                            )
                        }
                    },
                    actions = {

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                        ) {
                            Icon(
                                painterResource(
                                    id = R.drawable.serene_icon_bookmark
                                ),
                                contentDescription = null
                            )
                        }

                        IconButton(onClick = {}) {
                            Icon(
                                painterResource(
                                    id = R.drawable.serene_icon_more_vert
                                ),
                                contentDescription = null
                            )
                        }
                    },
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
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 36.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    ),
                ) {
                    Icon(
                        painterResource(
                            id = R.drawable.serene_selfcare_icon_environmental
                        ),
                        contentDescription = null,
                    )

                    Column {
                        Text(
                            "Change of space",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "Environmental Self-care",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Text(
                        "Change of space might be needed such as goin to the park. Cat ipsum dolor sit amet, annoy owner until he gives you food say meow repeatedly until belly rubs, feels good. "
                    )


                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        Text(
                            "General How to:",
                            style = MaterialTheme.typography.titleMedium
                        )
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
                            Destination.Practice.route
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Practice Self-care")


                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ActivityDetailScreenPreview() {
    SereneTheme {
        ActivityDetailScreen()
    }
}