package com.junnew.core.data.remote

import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthDto

    @POST("auth/register")
    suspend fun register(@Body req: RegisterRequest): AuthDto

    @POST("auth/logout")
    suspend fun logout()
}