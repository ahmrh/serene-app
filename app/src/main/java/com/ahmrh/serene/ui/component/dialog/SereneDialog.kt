package com.ahmrh.serene.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun SereneDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dismissText: String? = null,
    confirmText: String? = null,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector? = null,
) {
    AlertDialog(
        icon = {
            if (icon != null) {
                Icon(icon, contentDescription = "Example Icon")
            }
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText, textAlign = TextAlign.Justify)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(confirmText ?: "Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(dismissText ?: "Dismiss")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SereneDialogPreview() {
    SereneTheme {
        SereneDialog(
            onDismiss = {},
            onConfirm = {},
            dialogTitle = "test",
            dialogText = "text,"

        )
    }

}



