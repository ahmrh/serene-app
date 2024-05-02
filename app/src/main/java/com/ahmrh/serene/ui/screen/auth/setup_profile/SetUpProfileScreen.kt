package com.ahmrh.serene.ui.screen.auth.setup_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import org.checkerframework.checker.guieffect.qual.UI

@Composable
fun SetUpProfileScreen(
    navController: NavHostController,
    viewModel: SetUpProfileViewModel = hiltViewModel()
) {
    Surface {

        when(val uiState = viewModel.uiState.value){

            is AuthUiState.Success -> {
                LaunchedEffect(key1 = uiState) {
                    navController.navigate(Destination.Serene.route){
                        popUpTo(
                            Destination.Serene.route
                        ) {
                            inclusive =
                                true
                        }
                    }
                }
            }
            is AuthUiState.Loading -> {
                LoadingContent()
            }
            is AuthUiState.Error -> {
                Text(uiState.errorMessage)
            }

            is AuthUiState.Idle -> {

                Scaffold {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(it)

                            .padding(horizontal = 24.dp)
                            .padding(bottom = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )


                        Image(
                            painter = painterResource(id = R.drawable.serene_setup_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .wrapContentSize(
                                    unbounded = true,
                                    align = Alignment.Center
                                )
                                .size(width = 450.dp, height = 300.dp),
                            contentScale = ContentScale.Crop
                        )

                        Divider(
                            modifier = Modifier.padding(vertical = 24.dp)
                        )
                        Text("Set up your profile", style = MaterialTheme.typography.titleLarge)

                        Spacer(modifier = Modifier.height(24.dp))

                        Column (
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){

                            Button(
                                onClick = {
                                    viewModel.signInAnonymously()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Text("Continue Anonymously")
                            }

                            Button(
                                onClick = {
                                    navController.navigate(Destination.Auth.Register.route)
                                },
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                Text("Continue with Email")
                            }

//                                Text("OR")
//
//
//                                Button(
//                                    onClick = { index += 1},
//                                    modifier = Modifier.fillMaxWidth(),
//
//
//                                    ) {
//
//                                    Text("Continue with Google")
//                                }
                        }



                    }
                }
            }
        }
    }

}