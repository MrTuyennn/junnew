package com.junnew.core.domain.repository

import com.junnew.core.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Auth>
    suspend fun register(name: String, email: String, password: String): Auth
    fun currentUser(): StateFlow<Auth?>
    suspend fun logout()
}