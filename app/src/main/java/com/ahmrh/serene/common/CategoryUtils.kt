package com.ahmrh.serene.common

import com.ahmrh.serene.R

object CategoryUtils {

    fun getCategory(id: Int): Category =
        Category.fromId(id)

    fun getCategory(name: String): Category =
        Category.fromName(name)
}

enum class Category(
    val id: Int,
    val stringValue: String,

    val iconResource: Int,
    val imageResource: Int,
    val activityDescriptionResource: Int,
    val resultDescriptionResource: Int,
    val introductionDescriptionResource: Int,
) {
    EMOTIONAL(
        1,
        "Emotional",
        R.drawable.serene_selfcare_icon_emotional,
        R.drawable.serene_selfcare_image_emotional,
        R.string.serene_selfcare_activity_emotional,
        R.string.serene_selfcare_result_emotional,
        R.string.serene_selfcare_introduction_emotional
        ),
    ENVIRONMENTAL(
        2,
        "Environmental",
        R.drawable.serene_selfcare_icon_environmental,
        R.drawable.serene_selfcare_image_environmental,
        R.string.serene_selfcare_activity_environmental,
        R.string.serene_selfcare_result_environmental,
        R.string.serene_selfcare_introduction_environmental),
    MENTAL(
        3,
        "Mental",
        R.drawable.serene_selfcare_icon_mental,
        R.drawable.serene_selfcare_image_mental,
        R.string.serene_selfcare_activity_mental,
        R.string.serene_selfcare_result_mental,
        R.string.serene_selfcare_introduction_mental),
    PHYSICAL(
        4,
        "Physical",
        R.drawable.serene_selfcare_icon_physical,
        R.drawable.serene_selfcare_image_physical,
        R.string.serene_selfcare_activity_physical,
        R.string.serene_selfcare_result_physical,
        R.string.serene_selfcare_introduction_physical),
    RECREATIONAL(
        5,
        "Recreational",
        R.drawable.serene_selfcare_icon_recreational,
        R.drawable.serene_selfcare_image_recreational,
        R.string.serene_selfcare_activity_recreational,
        R.string.serene_selfcare_result_recreational,
        R.string.serene_selfcare_introduction_recreational
    ),
    SOCIAL(
        6,
        "Social",
        R.drawable.serene_selfcare_icon_social,
        R.drawable.serene_selfcare_image_social,
        R.string.serene_selfcare_activity_social,
        R.string.serene_selfcare_result_social,
        R.string.serene_selfcare_introduction_social
    ),
    SPIRITUAL(
        7,
        "Spiritual",
        R.drawable.serene_selfcare_icon_spiritual,
        R.drawable.serene_selfcare_image_spiritual,
        R.string.serene_selfcare_activity_spiritual,
        R.string.serene_selfcare_result_spiritual,
        R.string.serene_selfcare_introduction_spiritual
    );

    companion object {
        fun fromId(id: Int): Category =
            entries.single { it.id == id }
        fun fromName(name: String): Category =
            entries.single { it.stringValue == name }

        override fun toString(): String =
            entries.single().stringValue

    }
}