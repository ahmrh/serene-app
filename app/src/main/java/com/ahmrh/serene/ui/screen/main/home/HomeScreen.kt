package com.ahmrh.serene.ui.screen.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.component.card.ChallengeCard
import com.ahmrh.serene.ui.component.card.RecommendationCard
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold{
        Surface(
            modifier = Modifier.padding(it),
        ){

            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                ) {
                    Text("Welcome back", style = MaterialTheme.typography.bodyLarge)
                    Text("Mr. Hyobanshi", style = MaterialTheme.typography.titleMedium)
                }

                RecommendationSection()

                ChallengesSection()

            }
        }
    }

}

@Composable
fun RecommendationSection(){

    Column{
        Text("For you", style = MaterialTheme.typography.titleMedium)
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(5){
            RecommendationCard()
        }
    }
}

@Composable
fun ChallengesSection(){

    Column{
        Text("Today Challenges", style = MaterialTheme.typography.titleMedium)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        ChallengeCard(

        )
        ChallengeCard()
        ChallengeCard()
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SereneTheme {
        HomeScreen()
    }
}