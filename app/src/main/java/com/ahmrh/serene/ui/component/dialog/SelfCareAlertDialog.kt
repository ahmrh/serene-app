package com.ahmrh.serene.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SelfCareAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
        },
        title = {
            Text(text = "Hey,")
        },
        text = {
            Text(
                text = "Are you sure you already finish this Self-care?" +
                        " It’s good to follow through the general how to to gain " +
                        "the benefit in the long run",
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
                Text("Yeah")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Nah, please take me back!")
            }
        }
    )
}