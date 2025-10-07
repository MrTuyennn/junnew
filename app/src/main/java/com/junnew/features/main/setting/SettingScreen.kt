package com.junnew.features.main.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.junnew.design_system.component.box.IBox
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.features.main.setting.components.FeatureSetting
import com.junnew.features.main.setting.components.HeaderSetting
import com.junnew.features.main.setting.components.common.SettingsAction
import com.junnew.features.navigation.EditProfileRoute

@Composable
fun SettingScreen(
    nav: NavController
) {
    val color = MaterialTheme.appColors
    val dimens = MaterialTheme.dimens

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color.black)
            .statusBarsPadding()
            .padding(dimens.paddingDefault)
    ) {
        Column {
            HeaderSetting()
            IBox(height = dimens.paddingDefault)
            Box(modifier = Modifier.weight(1f)) {
                FeatureSetting { settingsAction ->
                    when (settingsAction) {
                        SettingsAction.OpenSettings -> nav.navigate(EditProfileRoute)
                        SettingsAction.PaymentMethods -> println("Open Settings")
                        SettingsAction.Notifications -> println("Open Settings")
                        SettingsAction.HelpCenter -> println("Open Settings")
                        SettingsAction.Feedback -> println("Open Settings")
                        SettingsAction.Logout -> println("Open Settings")
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Version", color = color.white, textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun PreViewSettingScreen() {
    SettingScreen(nav = NavController(LocalContext.current))
}