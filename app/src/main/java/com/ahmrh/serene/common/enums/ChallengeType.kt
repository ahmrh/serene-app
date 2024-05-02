package com.ahmrh.serene.common.enums

import com.ahmrh.serene.common.utils.Category

sealed class ChallengeType{

    data class PRACTICE(val category: Category): ChallengeType()

    data object PERSONALIZATION : ChallengeType()

    data object DEFAULT: ChallengeType()

    companion object{
        fun fromString(string: String, categoryString: String?): ChallengeType {
            return when(string){
                "Practice" ->  PRACTICE(Category.fromName(categoryString!!))
                "Personalization" -> PERSONALIZATION

                else -> DEFAULT
            }
        }
    }
}
