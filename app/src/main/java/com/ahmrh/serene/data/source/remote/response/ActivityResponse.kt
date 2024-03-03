package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ActivityResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("guide")
	val guide: List<String?>? = null,

	@field:SerializedName("benefit")
	val benefit: List<String?>? = null,

	@field:SerializedName("question")
	val question: String? = null,

)
