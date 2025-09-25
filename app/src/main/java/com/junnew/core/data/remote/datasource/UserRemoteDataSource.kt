package com.junnew.core.data.remote.datasource

import com.junnew.core.data.remote.AuthApi
import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest

class UserRemoteDataSource(private val authApi: AuthApi) {
   suspend fun loginAuth(loginRequest: LoginRequest): AuthDto {
     return  authApi.login(loginRequest)
   }

    suspend fun registerAuth(registerRequest: RegisterRequest): AuthDto {
        return authApi.register(registerRequest)
    }

   suspend fun logoutAuth() {
       authApi.logout()
   }
}