package com.ahmrh.serene.domain.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PersonalizationResult (

    // TODO: add user id to make it available to fetch again for registered user, also search how to put this into firebase
    val resultCategory: String? = null,

    val personalizationPoint: PersonalizationPoint,

    val date: Date


)

