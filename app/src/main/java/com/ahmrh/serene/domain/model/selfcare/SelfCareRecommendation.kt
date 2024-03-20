package com.ahmrh.serene.domain.model.selfcare

import com.ahmrh.serene.common.utils.Category

data class SelfCareRecommendation(
    val selfCareId: String? = null,
    val category: String? = null,
    val title: String? = null,
    val description: String? = null,
)
