package com.ahmrh.serene.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SelfCareHistoryResponse(

	@field:SerializedName("date")
	val date: Long? = null,

	@field:SerializedName("selfCareId")
	val selfCareId: String? = null,

	@field:SerializedName("selfCareCategory")
	val selfCareCategory: String? = null

)
