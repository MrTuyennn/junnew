package com.junnew.design_system.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun IButton(buttonType: ButtonType = ButtonType.BUTTON, onClick: () -> Unit) {
    Box {
        when (buttonType) {
            ButtonType.BUTTON -> {
                Button(onClick = {
                    onClick
                }) {
                    Text("Button")
                }
            }
            ButtonType.TONAL -> {
                FilledTonalButton(onClick = { onClick() }) {
                    Text("Tonal")
                }
            }
            ButtonType.OUTLINED -> {
                OutlinedButton(onClick = { onClick() }) {
                    Text("Outlined")
                }
            }
            ButtonType.ELEVATED -> {
                ElevatedButton(onClick = { onClick() }) {
                    Text("Elevated")
                }
            }
            ButtonType.TEXT -> {
                TextButton(
                    onClick = { onClick() }) {
                    Text("Text Button")
                }
            }
        }
    }


}