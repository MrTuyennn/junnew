package com.junnew.features.auth.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junnew.core.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase
) : ViewModel() {

    var ui by mutableStateOf(RegisterUIState())
        private set

    fun onNameChange(v: String) { ui = ui.copy(name = v, nameError = null, generalError = null) }
    fun onEmailChange(v: String) { ui = ui.copy(email = v, emailError = null, generalError = null) }
    fun onPasswordChange(v: String) { ui = ui.copy(password = v, passwordError = null, generalError = null) }

    private fun validate(): Boolean {
        val nameOk = ui.name.trim().length >= 2
        val emailOk = EMAIL_REGEX.matches(ui.email.trim())
        val passOk = ui.password.length >= 6
        ui = ui.copy(
            nameError = if (!nameOk) "Tên tối thiểu 2 ký tự" else null,
            emailError = if (!emailOk) "Email không hợp lệ" else null,
            passwordError = if (!passOk) "Mật khẩu tối thiểu 6 ký tự" else null
        )
        return nameOk && emailOk && passOk
    }

    fun submit(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!validate()) return@launch
        ui = ui.copy(isLoading = true, generalError = null)
        register(ui.name.trim(), ui.email.trim(), ui.password)
            .onSuccess { onSuccess() }
            .onFailure { e -> ui = ui.copy(generalError = e.message) }
        ui = ui.copy(isLoading = false)
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    }
}