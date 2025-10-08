package com.junnew.core.data.repository.user

import com.junnew.core.domain.entity.User
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun getInfoUser(id: String): User

    val currentUser: StateFlow<User>

    fun clearCurrentUser()

    suspend fun editProfile(user: User)

}