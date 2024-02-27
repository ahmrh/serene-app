package com.ahmrh.serene.ui.screen.main.achievement

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.AchievementItem
import com.ahmrh.serene.ui.theme.SereneTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementDetailScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, navigationIcon = {
                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(
                                id = R.drawable.serene_icon_close
                            ),
                            contentDescription = null,
                        )

                    }
                },
                actions = {

                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(
                                id = R.drawable.serene_icon_share
                            ),
                            contentDescription = null,
                        )

                    }
                })
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 36.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painterResource(id = R.drawable.serene_placeholder_achievement),
                contentDescription = null,
                Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("Servant of Serenity", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedFilterChip(
                selected = false,
                onClick = {  },
                label = {
                    Text("Dec 19, 2023", style = MaterialTheme.typography.titleSmall)
                })

            Spacer(modifier = Modifier.height(32.dp))
            Text("You earned this Achievement by keep doing Mental Self-care in 30 days in a row! \n Keep up the good work",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)
        }
    }

}

@Preview(name= "Light Mode", showBackground = true)
@Preview(name= "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AchievementDetailScreenPreview(){
    SereneTheme {
        AchievementDetailScreen()
    }

}