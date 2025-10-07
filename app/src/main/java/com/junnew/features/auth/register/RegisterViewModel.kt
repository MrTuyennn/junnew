package com.junnew.features.auth.register

import androidx.lifecycle.viewModelScope
import com.junnew.R
import com.junnew.core.domain.usecase.RegisterUseCase
import com.junnew.app.appcomponent.BaseViewModel
import com.junnew.utils.constants.Constants
import com.junnew.utils.helper.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase
) : BaseViewModel() {


    private val _ui = MutableStateFlow(RegisterUIState())
    val ui: StateFlow<RegisterUIState> = _ui.asStateFlow()

    fun onNameChange(v: String) {
        _ui.update { it.copy(name = v, nameError = null, generalError = null) }
    }

    fun onEmailChange(v: String) {
        _ui.update { it.copy(email = v, emailError = null, generalError = null) }
    }

    fun onPasswordChange(v: String) {
        _ui.update { it.copy(password = v, passwordError = null, generalError = null) }
    }

    private fun validate(): Boolean {
        val nameOk = _ui.value.name.trim().length >= 2
        val emailOk = Constants.EMAIL_REGEX.matches(_ui.value.email.trim())
        val passOk = _ui.value.password.length >= 6
        _ui.update {
            it.copy(
                emailError = if (!emailOk) UiText.StringResource(resId = R.string.txt_please_enter_your_email_address)  else null,
                nameError = if (!nameOk) UiText.StringResource(resId = R.string.txt_name_must_be_at_least_2_characters) else null,
                passwordError = if (!passOk) UiText.StringResource(resId = R.string.txt_password_must_be_at_least_6_characters)  else null
            )
        }

        return nameOk && emailOk && passOk
    }

    fun submit(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!validate()) return@launch
        _ui.update {
            it.copy(isLoading = true, generalError = null)
        }
        register(_ui.value.name.trim(), ui.value.email.trim(), ui.value.password)
            .onSuccess { onSuccess() }
            .onFailure { e ->
                _ui.update {
                    it.copy(generalError = e.message)
                }
            }
        _ui.update {
            it.copy(isLoading = false)
        }
    }

}