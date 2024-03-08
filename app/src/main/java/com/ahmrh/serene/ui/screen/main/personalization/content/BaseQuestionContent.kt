package com.ahmrh.serene.ui.screen.main.personalization.content

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
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
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationViewModel
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun BaseQuestionContent(
    viewModel: PersonalizationViewModel? = null,
    onBackHandler: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    BackHandler {
        onBackHandler()
    }

    Scaffold {
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
                            "What self-care category you never or rarely done in the past few days? or you just want to try practicing that self care category?",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )
                    }


                    Divider()


                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp
                        ),
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (index in 1..7) {

                            var selectedState by remember {
                                mutableStateOf(
                                    false
                                )
                            }
                            val category = CategoryUtils.getCategory(index)

                            BaseQuestionAnswerField(
                                category = category,
                                onClick = {
                                    selectedState = !selectedState

                                    if(!selectedState){
                                        viewModel?.removeLeastPracticedCategory(category)
                                    } else {
                                        viewModel?.addLeastPracticedCategory(category)
                                    }


                                },
                                selectedState = selectedState
                            )
                        }
                    }

                }

                Button(
                    onClick = onNext,
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
fun BaseQuestionAnswerField(
    category: Category,
    onClick: () -> Unit = {},
    selectedState: Boolean
) {

    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        colors =
        if (selectedState) CardDefaults.cardColors(
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
fun BaseQuestionContentPreview() {
    SereneTheme {
        BaseQuestionContent()
    }

}