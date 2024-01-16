package com.ahmrh.serene.domain.model

import com.google.gson.annotations.SerializedName

// TODO add type converter in room database
data class PersonalizationPoint(

	@field:SerializedName("1")
	val emotional: Int,

	@field:SerializedName("2")
	val environmental: Int,

	@field:SerializedName("3")
	val mental: Int,

	@field:SerializedName("4")
	val physical: Int,

	@field:SerializedName("5")
	val recreational: Int,

	@field:SerializedName("6")
	val social: Int,

	@field:SerializedName("7")
	val spiritual: Int
)
