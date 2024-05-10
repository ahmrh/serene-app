package com.ahmrh.serene.domain.model.gamification

import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.source.remote.response.AchievementResponse
import com.ahmrh.serene.data.source.remote.response.ChallengeResponse

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val challengeType: ChallengeType,
    val selfCareCategory: Category,
    val progress: Int,
    val isDone: Boolean,
)

fun Challenge.toChallengeResponse(): ChallengeResponse{
    return ChallengeResponse(
        description = description,
        progress = progress,
        challengeType = challengeType.stringValue,
        title = title,
        selfCareCategory = if(challengeType is ChallengeType.PRACTICE) challengeType.category.stringValue else null,
        isDone = isDone
    )
}

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

