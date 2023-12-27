package com.ahmrh.serene.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SurveyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
//            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Survey Alert")
        },
        text = {
            Text(
                text = "It seems you haven’t fill out our personalization" +
                        " survey, please care to fill out to determine what’s " +
                        "your need right now",
                textAlign = TextAlign.Justify
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    "Sure! I’ll fill out the survey"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Nah")
            }
        }
    )
}