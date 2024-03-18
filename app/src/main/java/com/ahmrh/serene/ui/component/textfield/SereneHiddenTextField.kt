package com.ahmrh.serene.ui.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SereneHiddenTextField(

    label: String,
    value: String,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit,
    isError: Boolean,
    errorText: String,
) {


    var isFocused by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .onFocusChanged {focusState ->
                isFocused = focusState.isFocused
            },
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
        },
        isError = isFocused && isError,
        supportingText = {
            if(isError && isFocused) Text(errorText)
        }
    )
}
