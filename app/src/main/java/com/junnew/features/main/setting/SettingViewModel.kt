package com.junnew.features.main.setting

import androidx.lifecycle.viewModelScope
import com.junnew.app.appcomponent.BaseViewModel
import com.junnew.core.domain.entity.User
import com.junnew.core.domain.usecase.EditProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val editUserUseCase: EditProfileUseCase,
): BaseViewModel() {
    val currentUser: StateFlow<User> = editUserUseCase.currentUser

    init {
        viewModelScope.launch { editUserUseCase.getInfoUser("") }
    }
}