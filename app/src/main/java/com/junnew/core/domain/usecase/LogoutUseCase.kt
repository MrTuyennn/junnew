package com.junnew.core.domain.usecase

import com.junnew.core.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.logout()
    }
}