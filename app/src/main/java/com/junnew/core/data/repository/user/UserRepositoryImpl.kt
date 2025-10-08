package com.junnew.core.data.repository.user

import com.junnew.core.domain.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl  @Inject constructor (): UserRepository {

    private val _currentUser = MutableStateFlow<User>( User(
        userName = "",
        phone = 0,
        email = ""
    ))


    override val currentUser: StateFlow<User> = _currentUser.asStateFlow()

    override suspend fun getInfoUser(id: String): User {
        _currentUser.value = User(
            userName = "JunCook",
            phone = 334580451,
            email = "nguyenngoctuyen188@gmail.com"
        )
        return User(
            userName = "JunCook",
            phone = 334580451,
            email = "nguyenngoctuyen188@gmail.com"
        )
    }

    override fun clearCurrentUser() {
        _currentUser.value = User(
            userName = "",
            phone = 0,
            email = ""
        )
    }

    override suspend fun editProfile(user: User) {
        _currentUser.value = user
    }
}