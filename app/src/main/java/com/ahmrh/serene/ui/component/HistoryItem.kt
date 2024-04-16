package com.ahmrh.serene.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.common.enums.Sentiment
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun HistoryItem(
    selfCareHistory: SelfCareHistory? = null
) {
    val category =
        CategoryUtils.getCategory(selfCareHistory?.selfCareCategory!!)

    ListItem(
        headlineContent = {
            Text(
                "${category.stringValue} Self-care",
                style = MaterialTheme.typography.bodySmall
            )
        },
        supportingContent = {
            Text(
                selfCareHistory.selfCareName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        leadingContent = {
            Box(
                modifier =
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(
                            8.dp
                        )
                    )
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painterResource(id = category.iconResource),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        trailingContent = {
            val sentiment =
                Sentiment.fromName(selfCareHistory.feedbackSentiment)
//            Text(DateUtils.getElapsedTime(selfCareHistory.date))
            Box(
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painterResource(id = sentiment.iconResource),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    )
}


@Preview
@Composable
fun HistoryItemPreview() {
    SereneTheme {
        HistoryItem()
    }
}