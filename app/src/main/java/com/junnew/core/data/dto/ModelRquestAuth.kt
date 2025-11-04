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

@Serializable
data class TopTracksResponse(
    val items: List<Track>,
    val total: Int,
    val limit: Int,
    val offset: Int
)

@Serializable
data class Track(
    val id: String,
    val name: String,
    val artists: List<Artist>,
    val album: Album? = null,
    val duration_ms: Int? = null,
    val popularity: Int? = null
)

@Serializable
data class Artist(
    val id: String,
    val name: String
)

@Serializable
data class Album(
    val id: String,
    val name: String,
    val images: List<Image>? = null
)

@Serializable
data class Image(
    val url: String,
    val width: Int? = null,
    val height: Int? = null
)