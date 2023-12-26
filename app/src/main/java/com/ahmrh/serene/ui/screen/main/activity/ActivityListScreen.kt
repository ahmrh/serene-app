package com.ahmrh.serene.ui.screen.main.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
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
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.component.ActivityItem
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ActivityListScreen(

) {

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
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.serene_icon_arrow_back),
                                contentDescription = null,
                                Modifier.size(24.dp)
                            )
                        }
                    }

                    Text(
                        "Environmental\nSelf-care",
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
                        painterResource(id = R.drawable.serene_selfcare_image_environmental),
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize(unbounded = true, align = Alignment.TopCenter)
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
                Modifier.padding(vertical = 24.dp)
            ){
                Text(
                    "Activity List",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )

                LazyColumn{
                    items(10){
                        ActivityItem()
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
        ActivityListScreen()
    }
}