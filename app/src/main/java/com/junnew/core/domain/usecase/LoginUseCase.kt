package com.junnew.core.domain.usecase

import com.junnew.core.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = runCatching {
        repository.login(email, password)
    }
}