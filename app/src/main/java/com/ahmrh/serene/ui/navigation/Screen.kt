package com.ahmrh.serene.ui.navigation

sealed class Screen(val route: String) {
    // Authentication
    object Login: Screen("login")
    object Register: Screen("register")


    // Main Application
    object Landing: Screen("landing")
    object Home: Screen("home")
    object Activity: Screen("activity"){
        object Detail: Screen("")
    }
    object Profile: Screen("profile")
    object Setting: Screen("setting")
    object Achievement: Screen("achievement")


}