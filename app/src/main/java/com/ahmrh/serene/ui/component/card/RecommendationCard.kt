package com.ahmrh.serene.ui.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareActivity
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier,
    selfCare: SelfCareActivity? = null,
    onClick: () -> Unit = {}
) {
//    val category = CategoryUtils.getCategory(selfCare.category)
    val category = CategoryUtils.getCategory(1)
    ElevatedCard(
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(
                10.dp
            )
        ) {

            Icon(
                painterResource(
                    id = category.iconResource
                ),
                contentDescription = "${category.name} Self-care Icon",
                modifier = Modifier
                    .size(32.dp),

                )
            Text(
                "Exercise",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                "Make a time to do some exercise today",
                style = MaterialTheme.typography.bodySmall,
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun RecommendationCardPreview() {
    SereneTheme {
        RecommendationCard()
    }
}