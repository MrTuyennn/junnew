package com.junnew.features.main.setting.components.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.junnew.utils.helper.UiText

data class SettingItem (
    val title: UiText,
    val icon: ImageVector,
    val action: SettingsAction,
    val showChevron: Boolean = true,
    val tint: Color? = null,
)

sealed interface  SettingsAction {
    data object OpenSettings : SettingsAction
    data object PaymentMethods : SettingsAction
    data object Notifications : SettingsAction
    data object HelpCenter : SettingsAction
    data object Feedback : SettingsAction
    data object Logout : SettingsAction
}
