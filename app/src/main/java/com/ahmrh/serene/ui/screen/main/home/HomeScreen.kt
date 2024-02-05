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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.ui.component.card.ChallengeCard
import com.ahmrh.serene.ui.component.card.RecommendationCard
import com.ahmrh.serene.ui.component.navbar.SereneNavBar
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            SereneNavBar(navController)
        }
    ){
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

                RecommendationSection(navController)

                ChallengesSection()

            }

        }
    }

}

@Composable
fun RecommendationSection(

    navController: NavHostController = rememberNavController()
){

    Column{
        Text("For you", style = MaterialTheme.typography.titleMedium)
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(5){
            RecommendationCard(
                onClick = {

                    navController.navigate(
                        Destination.ActivityDetail.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
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
            value = 1,
            maxValue = 5,
            title = "For your mental",
            description = "Do 5 mental Self-care today"
        )
        ChallengeCard(

            value = 4,
            maxValue = 10,
            title = "Beat the laziness",
            description = "Do 10 Physical Self-care today"
        )
        ChallengeCard(

            value = 2,
            maxValue = 10,
            title = "Mind your emotion",
            description = "Do 10 Emotional Self-care today"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SereneTheme {
        HomeScreen()
    }
}