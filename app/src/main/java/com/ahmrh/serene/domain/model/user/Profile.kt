package com.ahmrh.serene.domain.model.user

import android.net.Uri
import com.ahmrh.serene.domain.model.gamification.Achievement
import java.util.Date

//data class Profile(
//    val displayName: String,
//    val email: String,
//    val imgUri: Uri,
//    val joined: Date,
//    val dayStreak: Int,
//    val topSelfCare: String?,
//    val totalAchievement: Int,
//    val totalSelfCare: Int,
//    val isAnon: Boolean,
//
//    val historyList: List<SelfCareHistory>,
//    val achievementList: List<Achievement>
//)

data class Profile(
    val displayName: String,
    val email: String,
    val imgUri: Uri,
    val joined: Date,
    val isAnon: Boolean,
)
