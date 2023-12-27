package com.ahmrh.serene.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun AchievementItem(
    onClick: () -> Unit = {}
){

    Box(
        Modifier.size(120.dp)
    ){
        Image(
            painterResource(id = R.drawable.serene_placeholder_achievement),
            contentDescription = null,
            Modifier
                .wrapContentSize(unbounded = true, align = Alignment.BottomCenter)
                .size(140.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementItemPreview(){
    SereneTheme {
        AchievementItem()
    }
}