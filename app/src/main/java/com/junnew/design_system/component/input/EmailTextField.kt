package com.junnew.design_system.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.junnew.design_system.theme.appColors

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email Address",
    isError: Boolean = false,
    supportingText: String? = null,
) {
    val color = MaterialTheme.appColors

    val colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        errorTextColor = MaterialTheme.colorScheme.onSurface,

        focusedContainerColor = color.white,
        unfocusedContainerColor = color.white,
        disabledContainerColor = color.white,
        errorContainerColor = color.white,

        // Border
        focusedBorderColor = Color(0xFFDDDDDD),
        unfocusedBorderColor = Color(0xFFE9E9E9),
        disabledBorderColor = Color(0xFFE9E9E9),
        errorBorderColor = MaterialTheme.colorScheme.error,

        // Cursor
        cursorColor = color.grey,
        errorCursorColor = color.grey,

        // Label
        focusedLabelColor = color.grey,
        unfocusedLabelColor = color.grey,
        disabledLabelColor = color.grey.copy(alpha = 0.38f),
        errorLabelColor = MaterialTheme.colorScheme.error,

        // Supporting text (màu gợi ý lỗi)
        focusedSupportingTextColor = MaterialTheme.colorScheme.error,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.error,
        disabledSupportingTextColor = MaterialTheme.colorScheme.error.copy(alpha = 0.38f),
        errorSupportingTextColor = MaterialTheme.colorScheme.error
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(label, style = TextStyle(color = color.grey)) },
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = RoundedCornerShape(14.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        colors = colors,
        isError = isError,
        supportingText = {
            val msg = if (supportingText.isNullOrBlank()) " " else supportingText
            Text(msg, style = MaterialTheme.typography.labelSmall)
        }
    )
}
