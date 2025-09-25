package com.junnew.core.data.di

import com.junnew.core.data.remote.datasource.UserRemoteDataSource
import com.junnew.core.data.remote.dto.LoginRequest
import com.junnew.core.data.remote.dto.RegisterRequest
import com.junnew.core.domain.model.Auth
import com.junnew.core.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
): AuthRepository {

    private val current = MutableStateFlow<Auth?>(null)
    private val users = mutableMapOf<String, Pair<String, String>>()

    override suspend fun login(
        email: String,
        password: String
    ): Auth {
        delay(800)
        val record = users[email.lowercase()]
            ?: throw IllegalArgumentException("Email chưa đăng ký")
        if (record.second != password) throw IllegalArgumentException("Mật khẩu không đúng")

        val user = remoteDataSource.loginAuth(LoginRequest(email, password))

        return Auth(
            id = user.id,
            name = user.token,
            email = user.email
        ).also { current.value = it }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Auth {
        delay(1000)
        val key = email.lowercase()
        if (users.containsKey(key)) throw IllegalArgumentException("Email đã tồn tại")
//        users[key] = name to password
        val authRegister = remoteDataSource.registerAuth(RegisterRequest(email, password, name))
        return login(authRegister.email, password)
    }

    override fun currentUser(): Flow<Auth?> = current.asStateFlow()

    override suspend fun logout() {
        delay(200)
        current.value = null
        remoteDataSource.logoutAuth()
    }

}