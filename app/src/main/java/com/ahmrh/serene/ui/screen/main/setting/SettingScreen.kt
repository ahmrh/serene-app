package com.ahmrh.serene.ui.screen.main.setting

import android.Manifest
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.findActivity
import com.ahmrh.serene.openAppSettings
import com.ahmrh.serene.ui.navigation.Destination
import com.ahmrh.serene.ui.component.textfield.SerenePasswordTextField
import com.ahmrh.serene.ui.component.textfield.SereneTextField
import com.ahmrh.serene.ui.theme.SereneTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SettingViewModel = hiltViewModel()
) {

    val navigateToProfile = {
        navController?.navigate(
            Destination.Serene.Profile.route
        ) {
            popUpTo(
                navController.graph.findStartDestination().id
            ) {
                saveState = true
            }
        }
    }

    val profileUiState = viewModel.profileUiState.collectAsState().value

    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {

                },
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
                actions = {
//                    TextButton(
//                        onClick = {
//                            viewModel.saveChange()
//                            navigateToProfile()
//
//                        }
//                    ) {
//                        Text(
//                            "Save",
//                            style = MaterialTheme.typography.titleMedium
//                        )
//                    }


                })
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .clickable {
                    focusManager.clearFocus(true)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(90.dp)
                    .border(
                        4.dp,
                        MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(
                            100.dp
                        )
                    )
                    .clip(
                        RoundedCornerShape(
                            100.dp
                        )
                    )

            ) {

                Image(
                    painterResource(
                        id = R.drawable.serene_placeholder_profile_picture
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()

                )
            }

//            TextButton(onClick = {}) {
//                Text(
//                    "Change Avatar",
//                    style = MaterialTheme.typography.titleMedium
//                )
//            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                ),
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if(profileUiState is UiState.Success){
                    val profile = profileUiState.data

                    if(!profile.isAnon){

//                        var nameValue by rememberSaveable { mutableStateOf("${profile.displayName}") }
//                        SereneTextField(value = nameValue, label = "Name", onValueChange = { nameValue = it })
//
//                        var emailValue by rememberSaveable { mutableStateOf("${profile.email}") }
//                        SereneTextField(value = emailValue, label = "Email", onValueChange = { emailValue = it }, enabled = false)

//                        SereneButtonTextField(
//                            label = "Password", value = passwordValue,
//                            visible = false, onClick = { Log.d("debug", "bwaaa")}
//                        )

//                        var passwordValue by rememberSaveable{ mutableStateOf("password") }
//                        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
//                        SerenePasswordTextField(
//                            label = "Password", value = passwordValue, visible = isPasswordVisible,
//                            onValueChange = { passwordValue = it },
//                            onVisibilityChange = { isPasswordVisible = !isPasswordVisible })


                        Text("You are currently registered user")
                        Button(
                            onClick = {
                                viewModel.signOut()
                                navController.navigate(Destination.Auth.route){
                                    popUpTo(
                                        Destination.Serene.route
                                    ) {
                                        inclusive =
                                            true
                                    }
                                }
                            },
                            Modifier.fillMaxWidth()
                        ) {
                            Text("Sign Out")

                        }
                    } else {

                        Text("You are currently anon user, your progress won't be synced online", textAlign = TextAlign.Center)



                        Button(
                            onClick = {

                                navController.navigate(Destination.Auth.Register.route)
                            },
                            Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Text("Link Account")

                        }
                        Button(
                            onClick = {
                                viewModel.signOut()
                                navController.navigate(Destination.Auth.route){
                                    popUpTo(
                                        Destination.Serene.route
                                    ) {
                                        inclusive =
                                            true
                                    }
                                }
                            },
                            Modifier.fillMaxWidth()
                        ) {
                            Text("Sign Out")

                        }
                    }


                }





            }

            Spacer(Modifier.height(24.dp))

            Column (

                modifier = Modifier
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){

                Text(
                    "General",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )



                var notificationChecked by rememberSaveable {
                    mutableStateOf(
                        viewModel.notificationState.value
                    )
                }


                val context = LocalContext.current
                val activity = context.findActivity()
                val permissionState = rememberPermissionState(
                    permission = Manifest.permission.POST_NOTIFICATIONS
                ){ isGranted ->
                    if(!isGranted){
                        notificationChecked = false
                    }
                }

                notificationChecked = permissionState.status.isGranted

                val onNotificationChecked: (Boolean) -> Unit = { isChecked ->
                    notificationChecked = isChecked

                    if(notificationChecked){

                        if(permissionState.status.isGranted){
                            viewModel.scheduleNotificationReminder()
                            Log.d("SettingScreen", "Notification Scheduled")
                            scope.launch {
                                snackbarHostState.showSnackbar("Notification scheduled", duration = SnackbarDuration.Short)
                            }
                        }
                        else if(!permissionState.status.shouldShowRationale){
                            activity.openAppSettings()
                        }
                        permissionState.launchPermissionRequest()
                    } else {
                        viewModel.cancelAllNotificationReminder()
                        scope.launch {
                            snackbarHostState.showSnackbar("Notification removed", duration = SnackbarDuration.Short)
                        }
                        Log.d("SettingScreen", "Notification Canceled")
                    }

                }

                ListItem(
                    headlineContent = {
                        Text(
                            "Reminder Notification",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    supportingContent = {
                        Text(
                            "Notification to remind you to practice Self-care",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    trailingContent = {
                        Switch(
                            checked = notificationChecked,
                            onCheckedChange = onNotificationChecked
                        )
                    }

                )



                var darkModeChecked by rememberSaveable {
                    mutableStateOf(
                        viewModel.darkModeState.value
                    )
                }

                ListItem(
                    headlineContent = {
                        Text(
                            "Dark Mode",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    supportingContent = {
                        Text(
                            "To prevent harm to your eyes if you find this UI too bright",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    trailingContent = {
                        Switch(
                            checked = darkModeChecked,
                            onCheckedChange = {
                                darkModeChecked = !darkModeChecked
                                viewModel.changeDarkModeValue(darkModeChecked)
                            }
                        )
                    }

                )
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
fun SettingScreenPreview() {
    SereneTheme {
        SettingScreen(navController = rememberNavController())
    }

}