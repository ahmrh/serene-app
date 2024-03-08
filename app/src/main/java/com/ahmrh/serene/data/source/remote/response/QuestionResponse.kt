package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class QuestionResponse(

	@field:SerializedName("questionString")
	val questionString: QuestionString? = null,

	@field:SerializedName("category")
	val category: String? = null
)

data class QuestionString(

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
