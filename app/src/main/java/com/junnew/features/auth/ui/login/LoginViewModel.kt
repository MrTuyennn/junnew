package com.junnew.features.auth.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junnew.core.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase
) : ViewModel() {

    var ui by mutableStateOf(LoginUIState())
        private set

    fun onEmailChange(v: String) {
        print("email: $v")
        ui = ui.copy(email = v, emailError = null, generalError = null) }
    fun onPasswordChange(v: String) { ui = ui.copy(password = v, passwordError = null, generalError = null) }

    private fun validate(): Boolean {
        val emailOk = EMAIL_REGEX.matches(ui.email.trim())
        val passOk = ui.password.length >= 6
        ui = ui.copy(
            emailError = if (!emailOk) "Email không hợp lệ" else null,
            passwordError = if (!passOk) "Mật khẩu tối thiểu 6 ký tự" else null
        )
        return emailOk && passOk
    }

    fun submit(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!validate()) return@launch
        ui = ui.copy(isLoading = true, generalError = null)
        login(ui.email.trim(), ui.password)
            .onSuccess { onSuccess() }
            .onFailure { e -> ui = ui.copy(generalError = e.message) }
        ui = ui.copy(isLoading = false)
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    }
}