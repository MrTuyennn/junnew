package com.junnew.core.data.remote.service

import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.core.data.remote.dto.TokenPair
import com.junnew.core.data.remote.ultis.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthDto>

    @POST("auth/register")
    suspend fun register(@Body req: RegisterRequest): Response<AuthDto>

    @POST("auth/logout")
    suspend fun logout()

    @POST("auth/refresh")
     fun refresh(@Body req: String): TokenPair
}