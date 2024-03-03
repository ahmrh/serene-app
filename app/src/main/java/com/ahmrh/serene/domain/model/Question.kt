package com.ahmrh.serene.domain.model

import com.ahmrh.serene.common.Category

enum class Question(
    questionString: String
) {
    TYPE_1(
        questionString = "From below activities, which one you always did regularly?"
    ),

    TYPE_2(
        questionString = "From below activities, which one you never or rarely did?"
    ),

    TYPE_3(
        questionString = "From below activities, which one you have done in the past few days?"
    );

    companion object {

    }

}


enum class QuestionType {
    SelfCare,
    Frequency
}