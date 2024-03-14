package com.ahmrh.serene.navigation

sealed class Destination(val route: String) {
    // Authentication
    data object Login: Destination("login")
    data object Register: Destination("register")
    data object Landing: Destination("landing")
    data object Recovery: Destination("recovery")


    // Main Application
    data object Home: Destination("home")
    data object ActivityCategory: Destination("activity_category")
    data object ActivityList: Destination("activity_list?category={categoryId}"){
        fun createRoute(categoryId: Int) = "activity_list?category=$categoryId"
    }

    data object ActivityDetail: Destination("activity_detail?id={activityId}"){
        fun createRoute(activityId: String) = "activity_detail?id=$activityId"
    }
    data object Practice: Destination("practice?id={activityId}"){
        fun createRoute(activityId: String) = "practice?id=$activityId"
    }
    data object Question: Destination("question")

    data object Personalization: Destination("personalization")
    data object Result: Destination("result?category={categoryId}"){
        fun createRoute(categoryId: Int) = "result?category=$categoryId"
    }

    data object Profile: Destination("profile")
    data object Setting: Destination("setting")
    data object Achievement: Destination("achievement"){
        fun createRoute(id: Int) = "achievement/$id"
    }


    data object History: Destination("history"){
        fun createRoute(id: Int) = "history/$id"
    }


    data object Introduction: Destination("introduction/{index}"){
        fun createRoute(index: Int) = "Introduction/$index"
    }

}