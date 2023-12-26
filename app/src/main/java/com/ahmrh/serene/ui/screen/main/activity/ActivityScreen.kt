package com.ahmrh.serene.ui.screen.main.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.card.ActivityCard
import com.ahmrh.serene.ui.component.card.RecommendationCard
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ActivityScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold (
        bottomBar = {
            SereneNavBar()
        }
    ){
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp),
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Activities", style = MaterialTheme.typography.titleLarge)
                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            painterResource(id = R.drawable.serene_icon_bookmarks),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(7){ index ->
                        ActivityCard(categoryId = index + 1)
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ActivityScreenPreview() {
    SereneTheme {
        ActivityScreen()
    }
}