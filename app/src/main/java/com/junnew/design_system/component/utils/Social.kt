package com.junnew.design_system.component.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junnew.design_system.component.button.ButtonType
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.dimens

@Composable
fun Social() {
    val dimens = MaterialTheme.dimens
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IButton(
            modifier = Modifier.weight(1f).height(dimens.heightBtn),
            buttonType = ButtonType.OUTLINED,
            onClick = { }
        ) {
            Text("Facebook")
        }
        IButton(
            modifier = Modifier.weight(1f).height(dimens.heightBtn),
            buttonType = ButtonType.OUTLINED,
            onClick = { }
        ) {
            Text("Google")
        }
    }

}