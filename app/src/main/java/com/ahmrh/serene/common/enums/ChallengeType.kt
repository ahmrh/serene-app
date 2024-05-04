package com.ahmrh.serene.common.enums

import com.ahmrh.serene.common.utils.Category

sealed class ChallengeType(
    val stringValue: String
){

    data class PRACTICE(val category: Category): ChallengeType("Practice")

    data object PERSONALIZATION : ChallengeType("Personalization")

    data object DEFAULT: ChallengeType("Default")

    companion object{
        fun fromString(string: String, categoryString: String? = null): ChallengeType {
            return when(string){
                "Practice" ->  PRACTICE(Category.fromName(categoryString!!))
                "Personalization" -> PERSONALIZATION

                else -> DEFAULT
            }
        }

    }
}
