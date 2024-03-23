package com.ahmrh.serene.navigation

sealed class Destination(val route: String) {


    // Authentication
    data object Auth: Destination("auth") {
        data object Login: Destination("login")
        data object Register: Destination("register")
        data object Landing: Destination("landing")
        data object SetUpProfile: Destination("setup_profile")
        data object ForgotPassword: Destination("forgot_password")
    }

    data object Serene: Destination("serene"){
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
        data object AchievementList: Destination("achievement_list")
        data object AchievementDetail: Destination("achievement_detail?id={achievementId}"){
            fun createRoute(achievementId: String) = "achievement_detail?id=$achievementId"
        }

        data object History: Destination("history"){
            fun createRoute(id: Int) = "history/$id"
        }


        data object Introduction: Destination("introduction/{index}"){
            fun createRoute(index: Int) = "Introduction/$index"
        }

    }

}