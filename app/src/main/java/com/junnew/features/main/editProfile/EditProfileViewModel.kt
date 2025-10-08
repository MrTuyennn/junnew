package com.junnew.features.main.editProfile

import com.junnew.app.appcomponent.BaseViewModel
import com.junnew.core.domain.entity.User
import com.junnew.core.domain.usecase.EditProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
) : BaseViewModel() {
    val currentUser: StateFlow<User> = editProfileUseCase.currentUser

    suspend fun updateUser(user: User) {
        editProfileUseCase.editProfile(user)
    }
}