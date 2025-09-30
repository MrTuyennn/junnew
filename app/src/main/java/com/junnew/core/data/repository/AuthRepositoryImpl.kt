package com.junnew.core.domain.repository

import android.util.Log
import com.junnew.core.data.remote.datasource.UserRemoteDataSource
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.core.di.qualifiers.IoDispatcher
import com.junnew.core.domain.model.Auth
import com.junnew.design_system.constants.LogSystem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    @param:IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
): AuthRepository {

    private val current = MutableStateFlow<Auth?>(null)
    private val users = mutableMapOf<String, Pair<String, String>>()

    override  fun login(
        email: String,
        password: String
    ): Flow<Auth> {
        Log.d(LogSystem.LOG_LEVELS, "login : $email")
        val user = remoteDataSource.loginAuth(LoginRequest(email, password))

        return flow {
            val dto = user.first()
            val auth = Auth(
                id = dto.data?.id ?: "",
                name = dto.data?.token ?: "",
                email = dto.data?.email ?: "",
            )
            emit(auth)
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Auth {
       try {
           val user = remoteDataSource.registerAuth(RegisterRequest(email, password, name))
           val dto = user.body()
           val auth = Auth(
               id = dto?.id ?: "",
               name = dto?.token ?: "",
               email = dto?.email ?: "",
           )
           return auth
       } catch (e: Exception) {
           throw e
       }
    }

    override fun currentUser(): Flow<Auth?> = current.asStateFlow()

    override suspend fun logout() {
        delay(200)
        current.value = null
        "remoteDataSource.logoutAuth()"
    }

}