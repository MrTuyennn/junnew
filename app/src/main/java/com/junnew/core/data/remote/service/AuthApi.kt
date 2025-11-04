package com.junnew.core.data.remote.service

import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.core.data.remote.dto.TokenPair
import com.junnew.core.data.remote.dto.TopTracksResponse
import com.junnew.core.data.remote.ultis.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthDto>

    @POST("auth/register")
    suspend fun register(@Body req: RegisterRequest): Response<AuthDto>

    @POST("auth/logout")
    suspend fun logout()

    @POST("auth/refresh")
     fun refresh(@Body req: String): TokenPair

    @GET("v1/me/top/tracks")
    suspend fun getTopTracks(
        @Query("time_range") timeRange: String = "long_term",
        @Query("limit") limit: Int = 5
    ): Response<TopTracksResponse>
}