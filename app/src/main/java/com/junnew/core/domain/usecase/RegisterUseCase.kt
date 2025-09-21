package com.junnew.core.domain.usecase

import com.junnew.core.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String) = runCatching {
        repository.register(name, email, password)
    }
}