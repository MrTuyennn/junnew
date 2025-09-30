package com.junnew.core.data.remote.datasource

import android.util.Log
import com.junnew.core.data.remote.service.AuthApi
import com.junnew.core.data.remote.dto.AuthDto
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.core.data.remote.ultis.BaseApiResponse
import com.junnew.core.data.remote.ultis.Resource
import com.junnew.core.di.qualifiers.IoDispatcher
import com.junnew.utils.constants.LogSystem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val authApi: AuthApi,
    @param:IoDispatcher private val coroutineDispatcher: CoroutineDispatcher): BaseApiResponse() {
    fun loginAuth(loginRequest: LoginRequest): Flow<Resource<AuthDto>> {
        return flow {
            try {
                emit(safeApiCall { authApi.login(loginRequest) })
            } catch (e: Exception) {
                Log.e(LogSystem.LOG_LEVELS, "loginAuth error $e", e)
                emit(Resource.Error(e.message ?: "Unknown error occurred"))
            }
        }.flowOn(coroutineDispatcher)
    }

    suspend fun registerAuth(registerRequest: RegisterRequest): Response<AuthDto> {
       try {
           return authApi.register(registerRequest)
       } catch (e: Exception) {
           Log.e(LogSystem.LOG_LEVELS, "registerAuth error $e", e)
           throw e
       }
    }
//
//   suspend fun logoutAuth() {
//       authApi.logout()
//   }
}