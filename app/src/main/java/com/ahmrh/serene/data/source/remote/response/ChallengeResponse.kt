package com.ahmrh.serene.data.source.remote.response

import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.domain.model.gamification.Challenge
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

	@field:SerializedName("done")
	val isDone: Boolean? = null,


	@field:SerializedName("selfCareCategory")
	val selfCareCategory: String? = null
)

fun ChallengeResponse.toChallenge(id: String): Challenge {

	return Challenge(
		id = id,
		title = title
			?: "Unidentified Title",
		description = description
			?: "There is no description",
		challengeType = ChallengeType.fromString(challengeType ?: "Personalization", selfCareCategory
		),
		progress = progress ?: 0,
		selfCareCategory = Category.fromName(selfCareCategory ?: "Emotional"),
		isDone = isDone ?: false
	)
}
