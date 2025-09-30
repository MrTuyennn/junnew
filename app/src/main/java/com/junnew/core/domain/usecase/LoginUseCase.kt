package com.junnew.core.domain.usecase

import com.junnew.core.domain.model.Auth
import com.junnew.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
     operator fun invoke(email: String, password: String): Flow<Auth> =  repository.login(email, password)
}