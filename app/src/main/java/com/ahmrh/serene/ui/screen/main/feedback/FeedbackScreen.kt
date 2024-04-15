package com.ahmrh.serene.ui.screen.main.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun FeedbackScreen(){

    val onDismiss = {}

    Scaffold(
        topBar = {

        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .weight(weight = 1f, fill = false),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(text = "You've Practiced an Activity", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)

                    Text(text="How would you rate this Self-care Activity?")

                    var selectedSentiment by remember { mutableIntStateOf(2) }

                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp)

                    ){

                        val iconResources = listOf(
                            R.drawable.serene_icon_sentiment_very_dissatisfied,
                            R.drawable.serene_icon_sentiment_dissatisfied,
                            R.drawable.serene_icon_sentiment_neutral,
                            R.drawable.serene_icon_sentiment_satisfied,
                            R.drawable.serene_icon_sentiment_very_satisfied
                        )

                        for(i in 0..4){
                            SentimentButton(
                                onClick = {
                                    selectedSentiment = i
                                },
                                iconResource = iconResources[i],
                                selected = selectedSentiment == i,
                                modifier = Modifier.size(56.dp)
                            )
                        }
                    }


                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Continue")


                }
            }
        }

    }
}

@Composable
fun SentimentButton(
    onClick: () -> Unit = {},
    iconResource: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    // TODO: Continue this

    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if(selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant,
        ),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackScreenPreview(){
    SereneTheme {
        FeedbackScreen()
    }
}
