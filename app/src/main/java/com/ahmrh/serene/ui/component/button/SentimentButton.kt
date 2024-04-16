package com.ahmrh.serene.ui.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun SentimentButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    iconResource: Int,
    text: String,
    selected: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant,
            ),
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = modifier
            )
        }

        if (selected)
            Text(
                text, style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Visible,
                modifier = Modifier.width(80.dp),
                maxLines = 2,
                textAlign = TextAlign.Center
            )
    }

}