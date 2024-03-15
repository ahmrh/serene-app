package com.ahmrh.serene.ui.screen.auth.landing

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun LandingScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LandingViewModel = hiltViewModel()
) {
    val isLoggedIn = viewModel.isLoggedIn.collectAsState().value

    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            if (isLoggedIn) {
                LaunchedEffect(key1 = isLoggedIn) {

                    navController.navigate(Destination.Serene.route) {
                        popUpTo(
                            Destination.Serene.route
                        ) {
                            inclusive =
                                true
                        }
                    }
                }
            } else {


                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(
                                16.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.serene_icon_lotus
                                ), contentDescription = null
                            )
                            Text(
                                "Serene",
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                        Image(
                            painter = painterResource(
                                id = R.drawable.serene_landing
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .wrapContentSize(
                                    unbounded = true,
                                    align = Alignment.Center
                                )
                                .size(width = 450.dp, height = 300.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            "Self-care companion\n for better lifestyle",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )

                        Button(
                            onClick = {
                                navController.navigate(
                                    Destination.Auth.SetUpProfile.route
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),

                            ) {
                            Text("Get Started")
                        }

                    }


                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    SereneTheme {
        LandingScreen()
    }
}