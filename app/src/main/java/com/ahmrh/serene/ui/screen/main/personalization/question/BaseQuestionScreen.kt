package com.ahmrh.serene.ui.screen.main.personalization.question

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationViewModel
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun BaseQuestionScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: PersonalizationViewModel = hiltViewModel()
) {

    Scaffold{
        Surface(
            modifier = Modifier.padding(it)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 18.dp,
                        bottom = 36.dp
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    ),
                    modifier = Modifier.weight(1f)
                ) {


                    Box(
                        modifier = Modifier.padding(
                            vertical = 16.dp
                        )
                    ) {

                        Text(
                            "What self-care category you never done or rarely done in the past few days?",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )
                    }


                    Divider()

                    BaseQuestionAnswerSection()
                }

                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {

                    Text("Next")


                }
            }

        }

    }


}

@Composable
fun BaseQuestionAnswerSection() {


    Column(
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        ),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        for(index in 1..7){

            BaseQuestionAnswerField(
                category = CategoryUtils.getCategory(
                    index
                ),
            )
        }

    }


}

@Composable
fun BaseQuestionAnswerField(
    category: Category,
    onClick: () -> Unit = {}
) {

    var selectedState by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.clickable {
            selectedState = !selectedState
        },
        colors =
        if(selectedState) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) else CardDefaults.cardColors()

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .height(72.dp)
                .fillMaxWidth(),

            ) {
            Text(
                "${category.stringValue}\nSelf-care",
                style = MaterialTheme.typography.titleMedium
            )

            Box {

                Image(
                    painter = painterResource(
                        id = category.imageResource
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize(
                            unbounded = true,
                            align = Alignment.Center
                        )
                        .height(100.dp)
                )
            }


        }
    }


}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun BaseQuestionScreenPreview() {
    SereneTheme {
        BaseQuestionScreen()
    }

}