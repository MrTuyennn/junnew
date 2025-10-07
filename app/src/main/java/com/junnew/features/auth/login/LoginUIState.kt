package com.junnew.features.auth.login

import com.junnew.utils.helper.UiText

data class LoginUIState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val generalError: String? = null,
    val name: String = ""
)