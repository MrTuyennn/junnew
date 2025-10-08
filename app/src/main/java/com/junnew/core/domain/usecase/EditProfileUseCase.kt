package com.junnew.core.domain.usecase

import com.junnew.core.data.repository.user.UserRepository
import com.junnew.core.domain.entity.User
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    val currentUser: StateFlow<User> get() = userRepository.currentUser

    suspend fun getInfoUser(id: String) = runCatching { userRepository.getInfoUser(id) }

    suspend fun editProfile(user: User) = runCatching { userRepository.editProfile(user) }

    fun clearCurrentUser() = userRepository.clearCurrentUser()
}