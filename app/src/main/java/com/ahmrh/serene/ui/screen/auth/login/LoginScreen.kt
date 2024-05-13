package com.ahmrh.serene.ui.screen.auth.login

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.AuthUiState
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.component.textfield.SereneEmailTextField
import com.ahmrh.serene.ui.component.textfield.SerenePasswordTextField
import com.ahmrh.serene.ui.screen.main.activity.practice.LoadingContent
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = hiltViewModel()
) {

    var authUiState = viewModel.uiState.value
    var openErrorDialog by remember{ mutableStateOf(false) }


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
    var passwordVisible by remember {
        mutableStateOf(
            false
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {

                        navController.navigateUp()
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

        when(authUiState){
            is AuthUiState.Idle -> {

                val focusManager = LocalFocusManager.current
                Column(
                    Modifier
                        .padding(it)
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 64.dp)
                        .fillMaxSize()
                        .clickable {
                            focusManager.clearFocus(true)
                        },
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Login",
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

                            SereneEmailTextField(
                                value = emailValue,
                                label = "Email",
                                onValueChange = {
                                    emailValue = it
                                })

                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {

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

                                TextButton(
                                    onClick = {
                                        navController.navigate(Destination.Auth.ForgotPassword.route)
                                    }
                                ) {
                                    Text("Forgot password?", fontWeight = FontWeight.Bold)
                                }
                            }

                            TextButton(
                                onClick = {
                                    navController.navigate(Destination.Auth.Register.route){
                                        popUpTo(Destination.Auth.SetUpProfile.route) {
                                            saveState = true
                                        }
                                    }
                                }
                            ) {
                                Text("Don't have an account?", fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Button(
                        onClick = { viewModel.onLogin(emailValue, passwordValue) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Login")
                    }

                }
            }
            is AuthUiState.Loading -> {
                LoadingContent()
            }
            is AuthUiState.Success -> {
                LaunchedEffect(key1 = authUiState) {
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

                when{
                    openErrorDialog -> {

                        SereneDialog(
                            onDismiss = {
                                openErrorDialog = false
                            },
                            onConfirm = {
                                openErrorDialog = false
                            },
                            dialogTitle = "Oops",
                            dialogText = "${authUiState.errorMessage}",
                            icon = Icons.Default.Info
                        )
                    }
                }

            }
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
fun LoginScreenPreview() {
    SereneTheme {
        LoginScreen()
    }
}