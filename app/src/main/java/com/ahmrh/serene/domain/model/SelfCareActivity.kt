package com.ahmrh.serene.domain.model

import android.net.Uri

data class SelfCareActivity(
    val id: String,
    val imageUri: Uri?,
    val name: String? = "Activity name",
    val description: String? = "Our minds can become cluttered with worries, tasks, and emotions, leading to stress, anxiety, and difficulty focusing. Regularly \"organizing your thoughts\" helps clear this mental clutter, promoting clarity, peace of mind, and improved decision-making.",
    val category: String? = "Emotional",
    val guide: List<String?>? = listOf("Guide 1", "Guide 2", "Guide 3"),
    val benefit: List<String?>? = listOf("Benefit 1 (Author, year)", "Benefit 2 (Author, year)", "Benefit 3 (Author, year)", )
)
