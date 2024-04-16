package com.ahmrh.serene.common.enums

import com.ahmrh.serene.R


enum class Sentiment(
    val id: Int,
    val stringValue: String,
    val iconResource: Int
){
    VERY_DISSATISFIED(
        1,
        "Very Dissatisfied",
        R.drawable.serene_icon_sentiment_very_dissatisfied
    ),
    DISSATISFIED(

        2,
        "Dissatisfied",
        R.drawable.serene_icon_sentiment_dissatisfied
    ),
    NEUTRAL(
        3,
        "Neutral",
        R.drawable.serene_icon_sentiment_neutral
    ),
    SATISFIED(
        4,
        "Satisfied",
        R.drawable.serene_icon_sentiment_satisfied
    ),
    VERY_SATISFIED(
        5,
        "Very Satisfied",
        R.drawable.serene_icon_sentiment_very_satisfied
    );

    companion object {
        fun fromId(id: Int): Sentiment =
            Sentiment.entries.single { it.id == id }
        fun fromName(name: String): Sentiment =
            Sentiment.entries.single { it.stringValue == name }

    }
}