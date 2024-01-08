package com.ahmrh.serene.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun SereneButtonTextField(

    label: String,
    value: String,
    visible: Boolean,
    onClick: () -> Unit = {}

){
    val interactionSource = remember { MutableInteractionSource() } // just to disable ripple effect on clickable
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().clickable(interactionSource = interactionSource, indication = null) { onClick() },
        enabled = false,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        value = value,
        label = { Text(label) },
        onValueChange = {},
        trailingIcon = {},
        colors = TextFieldDefaults.colors(
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledIndicatorColor = MaterialTheme.colorScheme.outline
        )
    )
}