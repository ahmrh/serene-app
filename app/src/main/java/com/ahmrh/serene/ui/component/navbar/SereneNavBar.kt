package com.ahmrh.serene.ui.component.navbar

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.ahmrh.serene.R
import com.ahmrh.serene.navigation.Destination
import com.ahmrh.serene.ui.theme.SereneTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SereneNavBar(
    navigateToActivities: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    navigateToPersonalization: () -> Unit = {},
    navigateToPractice: () -> Unit = {},
    currentDestination: NavDestination? = null,
    selfCareStarted: Boolean? = null
) {
    NavigationBar {

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.List, contentDescription = "Activities"
                )
            },
            label = { Text("Activities") },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.ActivityCategory.route } == true,
            onClick = navigateToActivities
        )

        if (currentDestination?.hierarchy?.any { it.route == Destination.Home.route } == false) {

            NavigationBarItem(
                icon = {
                    Icon(
                        Icons.Filled.Home, contentDescription = "Home"
                    )
                },
                label = { Text("Home") },
                selected = false,
                onClick = navigateToHome
            )

        } else {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
            ) {
                SereneButton(
                    onClick = {
                       if(selfCareStarted == true) navigateToPractice()
                        else navigateToPersonalization()
                    },
                    selfCareStarted = selfCareStarted ?: false
                )
            }
        }


        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Person, contentDescription = "Profile"
                )
            },
            label = { Text("Profile") },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Profile.route } == true,
            onClick = navigateToProfile
        )

    }
}

@Composable
fun SereneButton(
    onClick: () -> Unit,
    selfCareStarted: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite transition")

    // Animate opacity from 0f to 1f and back continuously
    val animatedOpacity by infiniteTransition.animateFloat(
        initialValue = 0.25f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ), label = "Opacity Animation"
    )


    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(
                color = if (selfCareStarted) MaterialTheme.colorScheme.primaryContainer.copy(alpha = animatedOpacity)
                else Color.Transparent
            )
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
