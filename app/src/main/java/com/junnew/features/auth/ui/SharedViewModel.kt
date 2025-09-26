package com.junnew.features.auth.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val _userData = MutableStateFlow<String>("Initial User Data")
    val userData: StateFlow<String> = _userData.asStateFlow()

    fun updateUserData(newData: String) {
        _userData.value = newData
    }
}