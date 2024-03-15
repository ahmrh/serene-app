package com.ahmrh.serene.domain.model

import com.google.gson.annotations.SerializedName

data class PersonalizationPoint(
	var emotional: Int = 0,
	var environmental: Int = 0,
	var mental: Int = 0,
	var physical: Int = 0,
	var recreational: Int = 0,
	var social: Int = 0,
	var spiritual: Int = 0
)
fun PersonalizationPoint.toMap(): Map<String, Any> {
	val map = mutableMapOf<String, Any>()
	map["emotional"] = emotional
	map["environmental"] = environmental
	map["mental"] = mental
	map["physical"] = physical
	map["recreational"] = recreational
	map["social"] = social
	map["spiritual"] = spiritual
	return map.toMap()
}