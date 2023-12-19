package com.ahmrh.serene.ui.navigation

sealed class Screen(val route: String) {
    object Auth: Screen("auth"){
        object Login: Screen("login")
        object Register: Screen("register")
    }

    object Main: Screen(" main"){

        object Home: Screen("home")
        object Activity: Screen("activity")

        object Profile: Screen("profile"){
            object Setting: Screen("setting")
            object Achievement: Screen("achievement")
        }
    }

    object Landing: Screen("landing")
}