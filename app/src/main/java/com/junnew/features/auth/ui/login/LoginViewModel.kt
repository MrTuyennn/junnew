package com.junnew.features.auth.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junnew.core.domain.usecase.LoginUseCase
import com.junnew.utils.constants.LogSystem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.launch
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUIState())
    val ui: StateFlow<LoginUIState> = _ui.asStateFlow()

    fun onEmailChange(v: String) {
        _ui.update { it.copy(email = v, emailError = null, generalError = null) }
    }

    fun onPasswordChange(v: String) {
        _ui.update { it.copy(password = v, passwordError = null, generalError = null) }
    }

    private fun validate(): Boolean {
        val email = _ui.value.email.trim()
        val pass = _ui.value.password
        val emailOk = EMAIL_REGEX.matches(email)
        val passOk = pass.length >= 6
        _ui.update {
            it.copy(
                emailError = if (!emailOk) "Email không hợp lệ" else null,
                passwordError = if (!passOk) "Mật khẩu tối thiểu 6 ký tự" else null
            )
        }
        return emailOk && passOk
    }

    fun submit(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!validate()) return@launch
        _ui.update { it.copy(isLoading = true, generalError = null) }
        runCatching { login(_ui.value.email.trim(), _ui.value.password) }
            .onSuccess { onSuccess() }
            .onFailure { e -> _ui.update { it.copy(generalError = e.message) } }
        _ui.update { it.copy(isLoading = false) }
    }

    fun setName(name: String) = viewModelScope.launch {
        Log.e(LogSystem.LOG_LEVELS,name)
        _ui.update { it.copy(name = name) }
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    }
}
