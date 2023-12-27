package com.ahmrh.serene.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    iconResource: Int = R.drawable.serene_stat_icon_streak,
    value: String = "Null",
    label: String = "Label"
) {
    Card(
        modifier = modifier.clip(
            RoundedCornerShape(16.dp)
        )
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme
                .colorScheme
                .surfaceColorAtElevation(
                    2.dp
                ),

            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            Icon(
                painterResource(iconResource),
                contentDescription = null,
                Modifier.size(36.dp)
            )

            Column (
            ){
                Text(
                    "$value",
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    "$label",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatCardPreview() {
    SereneTheme {
        StatCard()
    }
}
