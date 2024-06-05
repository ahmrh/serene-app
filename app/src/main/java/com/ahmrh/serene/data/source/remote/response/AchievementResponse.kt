package com.ahmrh.serene.data.source.remote.response

import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.domain.model.personalization.PersonalizationPoint
import com.google.gson.annotations.SerializedName
import java.util.Date

data class AchievementResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("progress")
	val progress: Int? = null,

	@field:SerializedName("description")
	val description: Description? = null,

	@field:SerializedName("category")
	val category: String? = null
)

fun AchievementResponse.toMap(): Map<String, Any> {
	val map = mutableMapOf<String, Any>()
	map["image"] = "$image"
	map["name"] = "$name"
	map["progress"] = progress!!
	map["description"] = hashMapOf(
		"en" to description?.en,
		"id" to description?.id
	)
	map["category"] = "$category"
	return map.toMap()
}