package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ActivityResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("description")
	val description: Description? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("benefit")
	val benefit: Benefit? = null,

	@field:SerializedName("guide")
	val guide: Guide? = null
)

data class Name(

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Benefit(

	@field:SerializedName("en")
	val en: List<String?>? = null,

	@field:SerializedName("id")
	val id: List<String?>? = null
)

data class Description(

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Guide(

	@field:SerializedName("en")
	val en: List<String?>? = null,

	@field:SerializedName("id")
	val id: List<String?>? = null
)
