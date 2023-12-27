package com.ahmrh.serene.ui.navigation

sealed class Screen(val route: String) {
    // Authentication
    object Login: Screen("login")
    object Register: Screen("register")


    // Main Application
    object Landing: Screen("landing")
    object Home: Screen("home")
    object ActivityCategory: Screen("activity_category")
    object ActivityList: Screen("activity_list?category={categoryId}"){
        fun createRoute(categoryId: Int) = "activity_list?category=$categoryId"
    }

    object ActivityDetail: Screen("activity_detail"){
        fun createRoute(categoryId: Int) = "activity_detail"
    }
    object Practice: Screen("practice"){
        fun createRoute(categoryId: Int) = "practice"
    }
    object Question: Screen("question")
    object Result: Screen("result")

    object Profile: Screen("profile")
    object Setting: Screen("setting")
    object Achievement: Screen("achievement"){
        fun createRoute(id: Int) = "achievement/$id"
    }


    object History: Screen("history"){
        fun createRoute(id: Int) = "history/$id"
    }

}