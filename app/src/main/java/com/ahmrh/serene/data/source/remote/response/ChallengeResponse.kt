package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ChallengeResponse(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("progress")
	val progress: Int? = null,

	@field:SerializedName("challengeType")
	val challengeType: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("selfCareCategory")
	val selfCareCategory: String? = null
)
