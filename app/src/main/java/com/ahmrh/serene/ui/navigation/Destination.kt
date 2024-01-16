package com.ahmrh.serene.ui.navigation

sealed class Destination(val route: String) {
    // Authentication
    object Login: Destination("login")
    object Register: Destination("register")


    // Main Application
    object Landing: Destination("landing")
    object Home: Destination("home")
    object ActivityCategory: Destination("activity_category")
    object ActivityList: Destination("activity_list?category={categoryId}"){
        fun createRoute(categoryId: Int) = "activity_list?category=$categoryId"
    }

    object ActivityDetail: Destination("activity_detail"){
        fun createRoute(categoryId: Int) = "activity_detail"
    }
    object Practice: Destination("practice"){
        fun createRoute(categoryId: Int) = "practice"
    }
    object Question: Destination("question")
    object Result: Destination("result")

    object Profile: Destination("profile")
    object Setting: Destination("setting")
    object Achievement: Destination("achievement"){
        fun createRoute(id: Int) = "achievement/$id"
    }


    object History: Destination("history"){
        fun createRoute(id: Int) = "history/$id"
    }

}