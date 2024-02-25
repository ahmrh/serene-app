package com.ahmrh.serene.ui.screen.main.activity.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.CategoryUtils
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.domain.model.SelfCareActivity
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.component.ActivityItem
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ActivityListScreen(
    navController: NavHostController = rememberNavController(),
    categoryId: Int,
    viewModel: ActivityListViewModel = hiltViewModel()

) {
    val category = CategoryUtils.getCategory(categoryId)

    viewModel.getActivityByCategory(category)
    val activityListState = viewModel.activityListUiState.collectAsState()

    when(activityListState.value){
        is UiState.Loading -> {
            LoadingScreen()

        }
        is UiState.Error -> {
            Text("Error ")

        }
        is UiState.Success -> {

            val activityList = (activityListState.value as UiState.Success<List<SelfCareActivity>>).data
            ActivityListScreenContent(
                category = category,
                activityList = activityList,
                navigateToDetail = {
                    navController.navigate(Destination.ActivityDetail.createRoute(it))
                },
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }


}

@Composable
fun LoadingScreen(){

    Surface {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    }
}

@Composable
fun ActivityListScreenContent(
    category: Category,
    activityList: List<SelfCareActivity>,
    navigateToDetail: (id: String) -> Unit = {},
    navigateBack: () -> Unit = {},
){

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 24.dp)
            ){
                Column{

                    Row {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                painter = painterResource(id = R.drawable.serene_icon_arrow_back),
                                contentDescription = null,
                                Modifier.size(24.dp)
                            )
                        }
                    }

                    Text(
                        "${category.stringValue}\nSelf-care",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }



                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                ) {
                    Image(
                        painterResource(id = category.imageResource ),
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize(
                                unbounded = true,
                                align = Alignment.TopCenter
                            )
                            .size(180.dp)
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),

            ) {
            Column(
                Modifier.padding(vertical = 16.dp)
            ){
                Text(
                    "Activity List",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyColumn{
                    items(activityList){

                        ActivityItem(
                            onClick = {
                                navigateToDetail(it.id)
                            },
                            activity = it
                        )
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityListScreenPreview() {

    SereneTheme {
//        ActivityListScreen()
        LoadingScreen()
    }
}