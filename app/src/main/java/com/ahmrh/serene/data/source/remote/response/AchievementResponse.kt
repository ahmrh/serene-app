package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

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
