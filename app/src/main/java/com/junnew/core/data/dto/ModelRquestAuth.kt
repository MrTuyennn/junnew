package com.junnew.core.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)

@Serializable
data class AuthDto(
    val id: String,
    val email: String,
    val token: String
)

@Serializable
data class TokenPair(val accessToken: String, val refreshToken: String)