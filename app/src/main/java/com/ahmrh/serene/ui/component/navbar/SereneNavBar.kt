package com.ahmrh.serene.ui.component.navbar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme


@Composable
fun SereneNavBar(

) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Activities") },
            label = { Text("Activities") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight(),
        ){
            SereneButton (
                onClick = {}
            )
        }

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

    }
}

@Composable
fun SereneButton(onClick: () -> Unit){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(100),
            )
            .clickable {
                onClick()
            }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.serene_icon_lotus),
            contentDescription = "Serene Button",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(40.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SereneNavBarPreview() {
    SereneTheme {
        SereneNavBar()
    }
}
