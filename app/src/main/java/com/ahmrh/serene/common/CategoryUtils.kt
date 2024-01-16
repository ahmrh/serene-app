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
    val resultDescription: String,
) {
    EMOTIONAL(
        1,
        "Emotional",
        R.drawable.serene_selfcare_icon_emotional,
        R.drawable.serene_selfcare_image_emotional,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind.",
        "Learn healthy coping mechanisms for stress and negativity, and remember to celebrate your victories with open arms. This is where you dance with joy, embrace tears, and discover the strength within your emotional tapestry."
    ),
    ENVIRONMENTAL(
        2,
        "Environmental",
        R.drawable.serene_selfcare_icon_environmental,
        R.drawable.serene_selfcare_image_environmental,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind.",
        "Honor your deep connection to the Earth in this verdant space. Step outside, feel the sun on your skin, and breathe in the fresh air. Practice sustainable habits, minimize your environmental impact, and appreciate the beauty that surrounds you. "
    ),
    MENTAL(
        3,
        "Mental",
        R.drawable.serene_selfcare_icon_mental,
        R.drawable.serene_selfcare_image_mental,
        "Tidy up your thoughts, cultivate calm, and savor the silence of a peaceful mind.",
        "Step into the tranquil haven of your mind. Embrace mindfulness practices like meditation, journaling, or simply breathing deeply. Let go of anxieties like fallen leaves, and nourish your inner garden with affirmations and self-compassion."
    ),
    PHYSICAL(
        4,
        "Physical",
        R.drawable.serene_selfcare_icon_physical,
        R.drawable.serene_selfcare_image_physical,
        "Keep your psych up and fuel your body’s fire to feel the strength bloom within.",
        "Listen to the whispers of your body, this sacred temple that houses your spirit. Fuel your energy with nourishing foods and prioritize quality sleep to nurture this precious vessel. Remember, a healthy body is the foundation for a vibrant life."
    ),
    RECREATIONAL(
        5,
        "Recreational",
        R.drawable.serene_selfcare_icon_recreational,
        R.drawable.serene_selfcare_image_recreational,
        "Unleash your inner child, let your soul revel in the joy of play.",
        "Let your inner child loose. Explore new hobbies, rediscover old passions, and allow yourself the freedom to express without judgment. These are the threads that weave the tapestry of your unique spirit."
    ),
    SOCIAL(
        6,
        "Social",
        R.drawable.serene_selfcare_icon_social,
        R.drawable.serene_selfcare_image_social,
        "Stitch love into shared laughter and bask in the warmth of belonging.",
        "Make time for the people who make you feel seen and heard, engage in meaningful conversations, and build a support network that fosters belonging and joy. We are social creatures, and connection is vital for our well-being."
    ),
    SPIRITUAL(
        7,
        "Spiritual",
        R.drawable.serene_selfcare_icon_spiritual,
        R.drawable.serene_selfcare_image_spiritual,
        "Dive deep within, uncover your purpose's flame, and let your spirit dance to its own rhythm.",
        "Find solace in meditation, prayer, or simply spending time in nature. Seek meaning and purpose beyond the daily grind, and explore practices that connect you to your inner self and the universe around you."
    );

    companion object {
        fun fromId(id: Int): Category = entries.single { it.id == id }

        override fun toString(): String = entries.single().stringValue

    }
}