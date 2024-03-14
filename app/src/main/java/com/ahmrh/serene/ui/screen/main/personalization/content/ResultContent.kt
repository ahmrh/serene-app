package com.ahmrh.serene.ui.screen.main.personalization.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.ui.screen.main.personalization.PersonalizationViewModel
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ResultContent(
    viewModel: PersonalizationViewModel,
    navigateToActivities: (category: Category) -> Unit = {},
    navigateToHome: () -> Unit = {},
    onShare: () -> Unit = {},
    onClose: () -> Unit = {},
) {
    BackHandler {
        navigateToHome()
    }

    val categoryId = viewModel.resultCategoryState.collectAsState().value!!
    val category = CategoryUtils.getCategory(categoryId)

    Scaffold(
        topBar = {
            Row(
                Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                IconButton(onClick = navigateToHome) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_close
                        ),
                        contentDescription = null
                    )
                }

                IconButton(onClick = onShare) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.serene_icon_share
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
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 18.dp,
                        bottom = 36.dp
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = category.imageResource),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Based on your result, you might need",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "${category.stringValue} Self-care",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    Spacer(Modifier.height(8.dp))


                    Text(
                        stringResource(
                            id = category.resultDescriptionResource
                        ), style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "This result is based on how well you are on different self-care category",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            navigateToActivities(category)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Go to Activities")


                    }
                }

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ResultContentPreview() {
    SereneTheme {
//        ResultContent(
//            result = PersonalizationResult(category = "Social")
//        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ResultContentVariantPreview(){
//    SereneTheme {
//        ResultContentVariant()
//    }
//}