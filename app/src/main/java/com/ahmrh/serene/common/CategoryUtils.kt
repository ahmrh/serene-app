package com.ahmrh.serene.common

import com.ahmrh.serene.R

object CategoryUtils {

    fun getCategory(id: Int): Category = Category.fromId(id)
}

enum class Category(
    val id: Int,
    val stringValue: String,
    val icon: Int,
    val image: Int,
    val description: String,
) {
    EMOTIONAL(
        1,
        "Emotional",
        R.drawable.serene_selfcare_icon_emotional,
        R.drawable.serene_selfcare_image_emotional,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind."
    ),
    ENVIRONMENTAL(
        2,
        "Environmental",
        R.drawable.serene_selfcare_icon_environmental,
        R.drawable.serene_selfcare_image_environmental,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind."
    ),
    MENTAL(
        3,
        "Mental",
        R.drawable.serene_selfcare_icon_mental,
        R.drawable.serene_selfcare_image_mental,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind."
    ),
    PHYSICAL(
        4,
        "Physical",
        R.drawable.serene_selfcare_icon_physical,
        R.drawable.serene_selfcare_image_physical,
        "Keep your psych up and fuel your body’s fire to feel the strength bloom within."
    ),
    RECREATIONAL(
        5,
        "Recreational",
        R.drawable.serene_selfcare_icon_recreational,
        R.drawable.serene_selfcare_image_recreational,
        "Unleash your inner child, let your soul revel in the joy of play."
    ),
    SOCIAL(
        6,
        "Social",
        R.drawable.serene_selfcare_icon_social,
        R.drawable.serene_selfcare_image_social,
        "Stitch love into shared laughter and bask in the warmth of belonging."
    ),
    SPIRITUAL(
        7,
        "Spiritual",
        R.drawable.serene_selfcare_icon_spiritual,
        R.drawable.serene_selfcare_image_spiritual,
        "Dive deep within, uncover your purpose's flame, and let your spirit dance to its own rhythm."
    );

    companion object {
        fun fromId(id: Int): Category = entries.single { it.id == id }

        override fun toString(): String = entries.single().stringValue

    }
}