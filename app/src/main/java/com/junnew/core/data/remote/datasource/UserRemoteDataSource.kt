package com.junnew.core.data.remote.datasource

import android.util.Log
import com.junnew.core.data.remote.AuthApi
import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.design_system.constants.LogSystem
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val authApi: AuthApi) {
   suspend fun loginAuth(loginRequest: LoginRequest): AuthDto {
       Log.d(LogSystem.LOG_LEVELS,"loginAuth : $loginRequest")
    try {
        return  authApi.login(loginRequest)
    } catch (e: Exception) {
        Log.e(LogSystem.LOG_LEVELS, "loginAuth error $e", e)
        throw e
    }
   }

    suspend fun registerAuth(registerRequest: RegisterRequest): AuthDto {
        return authApi.register(registerRequest)
    }

   suspend fun logoutAuth() {
       authApi.logout()
   }
}