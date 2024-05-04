package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TodayChallengeResponse(

	@field:SerializedName("date_generated")
	val dateGenerated: String? = null,

	@field:SerializedName("challenges")
	val challenges: List<ChallengesItem?>? = null
)

data class ChallengesItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("progress")
	val progress: Int? = null,

	@field:SerializedName("challengeType")
	val challengeType: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("isDone")
	val isDone: Boolean? = null,

	@field:SerializedName("selfCareCategory")
	val selfCareCategory: String? = null
)
