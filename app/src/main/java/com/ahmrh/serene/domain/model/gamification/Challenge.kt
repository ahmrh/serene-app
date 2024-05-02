package com.ahmrh.serene.domain.model.gamification

import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val challengeType: ChallengeType,
    val progress: Int,
    val isDone: Boolean,
)

