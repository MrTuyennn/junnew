package com.junnew.core.domain.repository

import com.junnew.core.domain.model.Auth
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Auth
    suspend fun register(name: String, email: String, password: String): Auth
    fun currentUser(): Flow<Auth?>
    suspend fun logout()
}