package com.junnew.features.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.junnew.R
import com.junnew.core.domain.usecase.LoginUseCase
import com.junnew.app.appcomponent.BaseViewModel
import com.junnew.utils.constants.Constants
import com.junnew.utils.constants.LogSystem
import com.junnew.utils.helper.UiText
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
) : BaseViewModel() {

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
        val emailOk = Constants.EMAIL_REGEX.matches(email)
        val passOk = pass.length >= 6
        _ui.update {
            it.copy(
                emailError = (if (!emailOk) UiText.StringResource(resId = R.string.txt_please_enter_your_email_address) else null),
                passwordError = (if (!passOk) UiText.StringResource(resId = R.string.txt_password_must_be_at_least_6_characters) else null),
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

    fun clean() {
        _ui.update { LoginUIState() }
    }
}
