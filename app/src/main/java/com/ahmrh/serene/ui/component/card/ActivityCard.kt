package com.ahmrh.serene.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.data.source.local.room.entity.SelfCare
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ActivityCard(
    modifier: Modifier = Modifier,
    categoryId: Int = 1,
    onClick: () -> Unit = {}
) {

//    val category = CategoryUtils.getCategory(selfCare.category)
    val category = CategoryUtils.getCategory(categoryId)
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),

            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .clickable { onClick() }
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(132.dp)
                .width(328.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    category.stringValue,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    category.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                Image(
                    painterResource(id = category.image),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize(unbounded = true)
                        .size(155.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityCardPreview() {
    SereneTheme {
        ActivityCard()
    }
}