package com.ahmrh.serene.domain.model.gamification

import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.source.remote.response.AchievementResponse

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val challengeType: ChallengeType,
    val selfCareCategory: Category,
    val progress: Int,
    val isDone: Boolean,
)

fun Challenge.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()

    map["id"] = id
    map["title"] = title
    map["description"] = description
    map["challenge_type"] = challengeType.stringValue
    map["self_care_category"] = selfCareCategory.stringValue
    map["progress"] = progress
    map["is_done"] = isDone

    return map
}

