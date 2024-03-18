package com.ahmrh.serene.ui.component.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SerenePasswordTextField(

    label: String,
    value: String,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit,
    supportingText: @Composable (() -> Unit)? = null,
) {

    var isError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    val errorText = when(passwordError){
        PasswordError.LENGTH -> "Password must be at least 8 characters long."
        PasswordError.UPPER_CASE -> "Password must include at least one uppercase letter"
        PasswordError.LOWER_CASE -> "Password must include at least one lowercase letter"
        PasswordError.DIGIT ->"Password must include at least one number"
        PasswordError.NONE -> ""
    }

    SereneHiddenTextField(
        label = label, value = value, visible = visible,
        onValueChange = {
            onValueChange(it)
            passwordError = getPasswordError(it)
            isError = passwordError != PasswordError.NONE
        },
        isError = isError,
        onVisibilityChange = onVisibilityChange,
        errorText = errorText
    )
}

fun getPasswordError(password: String): PasswordError {

    val minLength = 8
    val onMinLength = password.length >= minLength
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }

    return when{
        !onMinLength -> PasswordError.LENGTH
        !hasUpperCase -> PasswordError.UPPER_CASE
        !hasLowerCase -> PasswordError.LOWER_CASE
        !hasDigit -> PasswordError.DIGIT
        else -> PasswordError.NONE
    }
}
enum class PasswordError{
    LENGTH,
    UPPER_CASE,
    LOWER_CASE,
    DIGIT,
    NONE
}
