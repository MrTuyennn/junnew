package com.junnew.features.main.setting.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.junnew.design_system.theme.appColors

@Composable
fun SettingRow(
    item: SettingItem,
    onClick: (SettingsAction) -> Unit,
) {
    val context = LocalContext.current
    val color = MaterialTheme.appColors
    val text = MaterialTheme.typography

    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item.action) },
        leadingContent = {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = color.white
            )
        },
        headlineContent = {
            Text(text = item.title.asString(context), style = text.bodyMedium.copy(
                color = color.white
            ))
        },
        trailingContent = {
            if (item.showChevron) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = color.white
                )
            }
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
    )
}
