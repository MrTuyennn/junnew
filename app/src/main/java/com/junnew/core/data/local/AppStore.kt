package com.junnew.core.data.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AppState(
    val name: String? = null,
    val isLoggedIn: Boolean = false
)

sealed interface AppAction {
    data class SetName(val value: String?) : AppAction
    data object LoginSuccess : AppAction
    data object Logout : AppAction
}

class AppStore @Inject constructor() {
    private val _state = MutableStateFlow(AppState())

    val state: StateFlow<AppState> = _state.asStateFlow()
}