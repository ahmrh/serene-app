package com.ahmrh.serene.common.enums

enum class Language(
    val code: String
) {
    EN("en"),
    ID("in");

    companion object {
        fun getCode() = entries.single().code
    }
}