package com.ahmrh.serene.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun DailyStreakFlame(
    modifier: Modifier = Modifier,
    textString: String = "S",
    variant: DailyStreakFlameVariant,
    enabled: Boolean = true,
) {
    val fontSize =
        if (variant == DailyStreakFlameVariant.LARGE)
            43.sp
        else
            16.sp

    val size =
        if (variant == DailyStreakFlameVariant.LARGE)
            148.dp
        else
            50.dp

    val paddingValues =

        if (variant == DailyStreakFlameVariant.LARGE)
            PaddingValues(start = 56.dp, top = 64.dp)
        else
            PaddingValues(start = 20.dp, top = 20.dp)

    val tint =
        if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline

    val textColor =
        if(enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outlineVariant


    Box(
        modifier = modifier.size(size),
    ) {
        Icon(
            painterResource(id = R.drawable.serene_icon_daily_streak),
            contentDescription = null,
            tint = tint
        )

        Text(
            text = textString,
            style = MaterialTheme.typography.displaySmall,
            fontSize = fontSize,

            color = textColor,
            modifier = Modifier.padding(paddingValues),
            fontWeight = FontWeight.Bold

        )
    }
}

enum class DailyStreakFlameVariant {
    LARGE, SMALL
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
fun DailyStreakFlamePreview() {
    SereneTheme {
        Column {

            DailyStreakFlame(variant = DailyStreakFlameVariant.LARGE)
            DailyStreakFlame(variant = DailyStreakFlameVariant.SMALL)
        }
    }
}