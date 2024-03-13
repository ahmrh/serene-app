package com.ahmrh.serene.ui.screen.main.personalization.content

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.ahmrh.serene.R
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.domain.model.PersonalizationQuestion
import com.ahmrh.serene.ui.screen.main.personalization.FrequencyAnswer
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationViewModel
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun QuestionContent(
    navigateToResult: (int: Int)-> Unit = {},
    questionList: List<PersonalizationQuestion> = listOf(),
    viewModel: PersonalizationViewModel
) {


    // TODO: idk why the next button doesn't work, gotta fix this
    var progress by remember { mutableStateOf(1) }
    val maxProgress = questionList.size

    val questionString = questionList[progress - 1].questionString
    val questionCategory = CategoryUtils.getCategory( questionList[progress - 1].category ?: "Default")

    val radioOptions = listOf(
        FrequencyAnswer.REGULARLY.stringValue,
        FrequencyAnswer.SOMETIMES.stringValue,
        FrequencyAnswer.OCCASIONALLY.stringValue,
        FrequencyAnswer.RARELY.stringValue,
        FrequencyAnswer.NEVER.stringValue,
    )

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
           ""
        )
    }

    val onBackPressed = {
        if(progress == 1) {
            viewModel?.changeToBaseType()
        } else {
            viewModel?.revertAnswerQuestion()
            progress -= 1
        }
    }

    Scaffold(
        topBar = {
            Row(
                Modifier.padding(top = 24.dp)
            ) {
                IconButton(onClick = {
                    onBackPressed()
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
                            text = "$questionString",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )
                    }


                    Divider()

                    AnswerSection(
                        selectedAnswer = selectedOption,
                        onAnswerSelected = onOptionSelected,
                        answers = radioOptions
                    )

                }
                val categoryId = viewModel.resultCategoryState.collectAsState().value


                when{
                    categoryId != null -> {
                        LaunchedEffect(key1 = categoryId) {
                            Log.d("PersonalizationQuestion", "category id = ${categoryId}")

                            navigateToResult(categoryId)
                        }

                    }
                }

                Button(
                    onClick = {
                        if(progress < maxProgress) {
                            if(selectedOption == "") return@Button
                            progress += 1
                            viewModel?.answerQuestion(questionCategory, selectedOption)
                            onOptionSelected("")
                        } else {
                            viewModel.calculateResult()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        if(progress < maxProgress) "Next"
                        else "Show Result"
                    )
                    
                }
            }

        }

    }

}

@Composable
fun AnswerSection(
    selectedAnswer: String,
    onAnswerSelected: (String) -> Unit,
    answers: List<String>
) {

    Column(
        Modifier.selectableGroup(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )

    ) {
        answers.forEach { answerText ->
            QuestionAnswerField(
                text = answerText,
                onClick = {
                    onAnswerSelected(answerText)
                },
                isSelected = (answerText == selectedAnswer)

            )
        }
    }
}

@Composable
fun QuestionAnswerField(
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
fun QuestionContentPreview() {
    SereneTheme {
//        QuestionContent()
    }
}
