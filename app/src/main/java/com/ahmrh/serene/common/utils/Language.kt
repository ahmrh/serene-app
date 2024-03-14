package com.ahmrh.serene.common.utils

enum class Language(
    val code: String
) {
    EN("en"),
    ID("in");

    companion object {
        fun getCode() = entries.single().code
    }
}