package com.ahmrh.serene.ui.screen.main.personalization.question

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.domain.model.QuestionType
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationViewModel
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun QuestionScreen(
    questionString: String = "",
    onNext: () -> Unit = {},
    onResult: () -> Unit = {},
    progress: Int = 0,
    maxProgress: Int = 0,
) {
    Scaffold(
        topBar = {
            Row(
                Modifier.padding(top = 24.dp)
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_arrow_back
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    ) {

        Surface(
            modifier = Modifier.padding(it)
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp
                    )
                    .padding(
                        top = 18.dp,
                        bottom = 36.dp
                    )
                    .fillMaxHeight(),
                Arrangement.SpaceBetween
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {

                        LinearProgressIndicator(
                            progress = (progress / maxProgress.toFloat()),
                            modifier = Modifier
                                .weight(1f)

                        )
                        Text(
                            "$progress of $maxProgress",
                            style = MaterialTheme.typography.labelMedium
                        )

                    }

                    Box(
                        modifier = Modifier.padding(
                            vertical = 16.dp
                        )
                    ) {

                        Text(
                            text = questionString,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )
                    }


                    Divider()

                    AnswerSection()
                }

                Button(
                    onClick = if(maxProgress < progress) onNext else onResult,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        if(progress < maxProgress) "Next" else "Show Result"
                    )
                }
            }

        }

    }

}

@Composable
fun AnswerSection() {
    val radioOptions = listOf(
        "Regularly",
        "Sometimes",
        "Occasionally",
        "Rarely",
        "Never",
    )

    var (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            radioOptions[0]
        )
    }

    Column(
        Modifier.selectableGroup(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )

    ) {
        radioOptions.forEach { answerText ->
            FrequencyAnswerField(
                text = answerText,
                onClick = {

                    onOptionSelected(answerText)
                },
                isSelected = (answerText == selectedOption)

            )
        }
    }
}

@Composable
fun FrequencyAnswerField(
    text: String = "",
    onClick: () -> Unit = {},
    isSelected : Boolean = false
){

    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(
                    4.dp
                )
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = isSelected,
            onClick = null // null recommended for accessibility with screenreaders
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    SereneTheme {
        QuestionScreen()
    }
}
