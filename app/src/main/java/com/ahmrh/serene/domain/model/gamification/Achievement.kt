package com.ahmrh.serene.domain.model.gamification

import android.net.Uri
import com.ahmrh.serene.data.source.remote.response.Description
import com.google.gson.annotations.SerializedName

data class Achievement(
    val id: String? = null,

    val imageUri: Uri? = null,

    val imagePath: String? = null,

    val name: String? = null,

    val progress: Int? = null,

    val description: String? = null,

    val category: String? = null
)