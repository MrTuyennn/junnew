package com.junnew.design_system.component.button

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junnew.design_system.theme.appColors

@Composable
fun IButton(
    buttonType: ButtonType = ButtonType.BUTTON,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent
    ),
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val shape = MaterialTheme.shapes
    Box {
        when (buttonType) {
            ButtonType.BUTTON -> {
                Button(
                    modifier = modifier,
                    shape = shape.small,
                    colors = colors,
                    enabled = enabled,
                    onClick = {
                        onClick()
                    }) {
                    content()
                }
            }

            ButtonType.TONAL -> {
                FilledTonalButton(
                    modifier = modifier,
                    onClick = { onClick() }) {
                    Text("Tonal")
                }
            }

            ButtonType.OUTLINED -> {
                OutlinedButton(
                    modifier = modifier,
                    shape = shape.small,
                    enabled = enabled,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.appColors.grey
                    ),
                    onClick = {
                        onClick()
                    }) {
                    content()
                }
            }

            ButtonType.ELEVATED -> {
                ElevatedButton(
                    modifier = modifier,
                    onClick = { onClick() }) {
                    Text("Elevated")
                }
            }

            ButtonType.TEXT -> {
                TextButton(
                    modifier = modifier,
                    onClick = { onClick() }) {
                    Text("Text Button")
                }
            }
        }
    }
}
