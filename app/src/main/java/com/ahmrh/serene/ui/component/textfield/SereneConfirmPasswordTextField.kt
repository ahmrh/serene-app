package com.ahmrh.serene.ui.component.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SereneConfirmPasswordTextField(
    label: String,
    value: String,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit,
    password: String,
){

    var isError by rememberSaveable { mutableStateOf(false) }
    val errorMessage = "Password does not match."

    SereneHiddenTextField(
        label = label, value = value, visible = visible,
        onValueChange = {
            onValueChange(it)
            isError = password != it
        },
        isError = isError,
        onVisibilityChange = onVisibilityChange,
        errorText = errorMessage
    )
}