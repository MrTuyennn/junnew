package com.junnew.features.auth.register

import com.junnew.utils.helper.UiText

data class RegisterUIState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val nameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val generalError: String? = null
)