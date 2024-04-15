package com.ahmrh.serene.domain.model.gamification

import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.domain.handler.ChallengeType

data class Challenge(
    val name: String,
    val challengeType: ChallengeType,
    val progress: Int,
    val isDone: Boolean,
)

