package com.ahmrh.serene.ui.screen.auth.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.component.textfield.SereneConfirmPasswordTextField
import com.ahmrh.serene.ui.component.textfield.SereneEmailTextField
import com.ahmrh.serene.ui.component.textfield.SereneHiddenTextField
import com.ahmrh.serene.ui.component.textfield.SerenePasswordTextField
import com.ahmrh.serene.ui.component.textfield.SereneTextField
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(

    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    // TODO: Register firebase

    val uiState = viewModel.uiState.value

    var nameValue by remember {
        mutableStateOf(
            ""
        )
    }
    var emailValue by remember {
        mutableStateOf(
            ""
        )
    }
    var passwordValue by remember {
        mutableStateOf(
            ""
        )
    }
    var confirmPasswordValue by remember {
        mutableStateOf(
            ""
        )
    }

    var passwordVisible by remember {
        mutableStateOf(
            false
        )
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(
            false
        )
    }


    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {

                    navController.popBackStack()
                }) {

                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_arrow_back
                        ),
                        contentDescription = null,
                    )

                }
            },
        )
    }
    )
    {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 24.dp)
                .padding(bottom = 64.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                Text(
                    "Register",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Please fill your registered credential below",
                    style = MaterialTheme.typography.bodyMedium
                )

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {

                    SereneTextField(
                        value = nameValue,
                        label = "Username",
                        onValueChange = {
                            nameValue = it
                        })

                    SereneEmailTextField(
                        value = emailValue,
                        label = "Email",
                        onValueChange = {
                            emailValue = it
                        })

                    SerenePasswordTextField(
                        label = "Password",
                        value = passwordValue,
                        visible = passwordVisible,
                        onValueChange = {
                            passwordValue = it
                        }, onVisibilityChange = {
                            passwordVisible =
                                !passwordVisible
                        })


                    SereneConfirmPasswordTextField(
                        label = "Confirm Password",
                        value = confirmPasswordValue,
                        visible = confirmPasswordVisible,
                        onValueChange = {
                            confirmPasswordValue = it
                        }, onVisibilityChange = {
                            confirmPasswordVisible =
                                !confirmPasswordVisible
                        }, password = passwordValue
                        )


                    TextButton(onClick = {
                        navController.navigate(Destination.Auth.Login.route){
                            popUpTo(Destination.Auth.SetUpProfile.route) {
                                saveState = true
                            }
                        }
                    }) {
                        Text("Already have an account?", fontWeight = FontWeight.Bold)
                    }
                }

            }
            Button(
                onClick = {
                  viewModel.onRegister(emailValue, passwordValue)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")

            }

        }

    }

    var openErrorDialog by remember{ mutableStateOf(false) }

    when(uiState){
        is AuthUiState.Idle -> {

        }
        is AuthUiState.Loading -> {
            LoadingContent()
        }
        is AuthUiState.Success -> {
            LaunchedEffect(key1 = uiState) {
                navController.navigate(Destination.Serene.route) {
                    popUpTo(
                        Destination.Auth.route
                    ) {
                        inclusive =
                            true
                    }
                }
            }
        }
        is AuthUiState.Error -> {
            LaunchedEffect(key1 = null) {
                openErrorDialog = true
            }
            SereneDialog(
                onDismiss = {
                    openErrorDialog = false
                },
                onConfirm = {
                    openErrorDialog = false

                },
                dismissText = "Dismiss",
                dialogTitle = "Oops",
                dialogText = "Error: ${uiState.errorMessage}",
                icon = Icons.Default.Info
            )

        }
    }
}

@Preview(
    name = "Light Mode", showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun RegisterScreenPreview() {
    SereneTheme {
        RegisterScreen( rememberNavController() )
    }
}