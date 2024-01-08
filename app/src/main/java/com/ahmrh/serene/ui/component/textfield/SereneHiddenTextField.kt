package com.ahmrh.serene.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SereneHiddenTextField(

    label: String,
    value: String,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit,
    onClick: () -> Unit = {}
) {

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        trailingIcon = {

            val image = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(
                onClick = onVisibilityChange) {
                Icon(
                    imageVector = image,
                    contentDescription = null
                )

            }
        }
    )
}
