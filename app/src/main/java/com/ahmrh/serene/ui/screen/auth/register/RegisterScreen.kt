package com.ahmrh.serene.ui.screen.auth.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.textfield.SereneHiddenTextField
import com.ahmrh.serene.ui.component.textfield.SereneTextField
import com.ahmrh.serene.ui.theme.SereneTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(

    navController: NavHostController = rememberNavController(),
) {

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
                        16.dp
                    )
                ) {

                    var nameValue by remember {
                        mutableStateOf(
                            ""
                        )
                    }
                    SereneTextField(
                        value = nameValue,
                        label = "Username",
                        onValueChange = {
                            nameValue = it
                        })
                    var emailValue by remember {
                        mutableStateOf(
                            ""
                        )
                    }
                    SereneTextField(
                        value = emailValue,
                        label = "Email",
                        onValueChange = {
                            emailValue = it
                        })

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
                    SereneHiddenTextField(
                        label = "Password",
                        value = passwordValue,
                        visible = passwordVisible,
                        onValueChange = {
                            passwordValue = it
                        }, onVisibilityChange = {
                            passwordVisible =
                                !passwordVisible
                        })

                    var confirmPasswordValue by remember {
                        mutableStateOf(
                            ""
                        )
                    }
                    var confirmPasswordVisible by remember {
                        mutableStateOf(
                            false
                        )
                    }
                    SereneHiddenTextField(
                        label = "Confirm Password",
                        value = confirmPasswordValue,
                        visible = confirmPasswordVisible,
                        onValueChange = {
                            confirmPasswordValue = it
                        }, onVisibilityChange = {
                            confirmPasswordVisible =
                                !confirmPasswordVisible
                        })

                    TextButton(onClick = {}) {
                        Text("Already have an account?", fontWeight = FontWeight.Bold)
                    }
                }

            }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")

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
fun RegisterScreenPreview() {
    SereneTheme {
        RegisterScreen()
    }
}