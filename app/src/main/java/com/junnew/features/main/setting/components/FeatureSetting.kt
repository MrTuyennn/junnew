package com.junnew.features.main.setting.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Output
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junnew.R
import com.junnew.features.main.setting.components.common.SettingItem
import com.junnew.features.main.setting.components.common.SettingRow
import com.junnew.features.main.setting.components.common.SettingsAction
import com.junnew.utils.helper.UiText

@Composable
fun FeatureSetting(
    onAction: (SettingsAction) -> Unit
) {
    val items = remember {
        listOf(
            SettingItem(UiText.StringResource(resId = R.string.txt_setting), Icons.Outlined.Settings, SettingsAction.OpenSettings),
            SettingItem(UiText.StringResource(R.string.txt_payment_methods), Icons.Outlined.CreditCard, SettingsAction.PaymentMethods),
            SettingItem(UiText.StringResource(R.string.txt_notifications), Icons.Outlined.Notifications, SettingsAction.Notifications),
            SettingItem(UiText.StringResource(R.string.txt_help_center), Icons.Outlined.HelpOutline, SettingsAction.HelpCenter),
            SettingItem(UiText.StringResource(R.string.txt_give_us_feedback), Icons.Outlined.Edit, SettingsAction.Feedback),
            SettingItem(
                UiText.StringResource(R.string.txt_log_out),
                Icons.Outlined.Output,
                SettingsAction.Logout,
                showChevron = false,
            )
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        itemsIndexed(items) { idx, item ->
            SettingRow(item = item, onClick = onAction)
        }
    }
}

@Preview
@Composable
fun PreViewFeatureSetting() {
    FeatureSetting(
        onAction = {}
    )
}