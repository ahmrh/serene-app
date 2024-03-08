package com.ahmrh.serene.domain.model

import com.ahmrh.serene.data.source.remote.response.QuestionString
import com.google.gson.annotations.SerializedName

data class PersonalizationQuestion(
    val questionString: String? = null,
    val category: String? = null
)
