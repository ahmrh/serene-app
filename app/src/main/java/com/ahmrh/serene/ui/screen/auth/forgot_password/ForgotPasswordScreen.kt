package com.ahmrh.serene.ui.screen.auth.forgot_password

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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.dialog.SereneDialog
import com.ahmrh.serene.ui.component.textfield.SereneEmailTextField
import com.ahmrh.serene.ui.component.textfield.SereneTextField
import com.ahmrh.serene.ui.theme.SereneTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    var openConfirmationDialog by remember { mutableStateOf(false) }

    var emailValue by remember {
        mutableStateOf(
            ""
        )
    }

    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {}) {

                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_arrow_back
                        ),
                        contentDescription = null,
                    )

                }
            },
        )
    }) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 24.dp)
                .padding(bottom = 64.dp)
                .fillMaxSize()
                .clickable{
                      focusManager.clearFocus(true)
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                Text(
                    "Forgot Password",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Please fill your registered email",
                    style = MaterialTheme.typography.bodyMedium
                )

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    )
                ) {

                    SereneEmailTextField(
                        value = emailValue,
                        label = "Email",
                        onValueChange = {
                            emailValue = it
                        })

                }

            }
            Button(
                onClick = {
                    viewModel.recoverPassword(emailValue)
                    openConfirmationDialog = true


                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit")

            }

        }

    }



    when{
        openConfirmationDialog -> {

            SereneDialog(
                onDismiss = {
                    openConfirmationDialog = false
                    navController.navigateUp()
                },
                onConfirm = {
                    openConfirmationDialog = false
                    navController.navigateUp()

                },
                dialogTitle = "Check your email",
                dialogText = "We've sent recovery password to $emailValue. Please follow further instruction there.",
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
fun ForgotPasswordScreenPreview() {
    SereneTheme {
        ForgotPasswordScreen()
    }
}