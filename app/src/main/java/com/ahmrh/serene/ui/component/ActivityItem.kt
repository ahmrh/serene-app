package com.ahmrh.serene.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareActivity
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    selfCare: SelfCareActivity? = null,
    onClick: () -> Unit = {}
) {
    val category = CategoryUtils.getCategory(selfCare?.categoryId?.toInt() ?: 1 )
    Column(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                headlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
                supportingColor = MaterialTheme.colorScheme.onSurface,
            ),
            headlineContent = {
                Text(
                    "${category.stringValue} Self-care",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            supportingContent = {
                Text(selfCare?.name ?: "Unidetified Self-care", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            },
            leadingContent = {
                Icon(
                    painterResource(id = category.iconResource),
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {

                Icon(
                    painterResource(R.drawable.serene_icon_arrow_right),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        Divider()
    }

}

@Preview(showBackground = true)
@Composable
fun ActivityItemPreview() {
    SereneTheme {
        ActivityItem()
    }
}