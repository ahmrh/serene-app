package com.ahmrh.serene.domain.model.user

data class Auth(
    val displayName: String,
    val password: String,
    val email: String,
    val isAnon: Boolean,
)
