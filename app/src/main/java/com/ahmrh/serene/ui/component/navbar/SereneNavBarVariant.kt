package com.ahmrh.serene.ui.component.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import com.ahmrh.serene.ui.theme.SereneTheme


@Composable
fun SereneNavBarVariant(

) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Activities") },
            label = { Text("Activities") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {

            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

    }
}


@Preview(showBackground = true)
@Composable
fun SereneNavBarVariantPreview() {
    SereneTheme {
        SereneNavBarVariant()
    }
}
