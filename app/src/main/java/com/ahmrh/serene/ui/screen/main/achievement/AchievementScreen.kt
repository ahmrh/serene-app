package com.ahmrh.serene.ui.screen.main.achievement

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.AchievementItem
import com.ahmrh.serene.ui.theme.SereneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, navigationIcon = {
                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(
                                id = R.drawable.serene_icon_arrow_back
                            ),
                            contentDescription = null,
                        )

                    }
                })
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                minSize = 168.dp
            ), modifier = Modifier.padding(it).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(10) {
                AchievementItem(variant = 2)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AchievementScreenPreview() {
    SereneTheme {
        AchievementScreen()
    }
}