package com.junnew.features.auth.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OrDivider(
    modifier: Modifier = Modifier,
    text: String = "Or sign in with",
    lineColor: Color= MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    gap: Dp = 12.dp,
    thickness: Dp = 1.dp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(thickness),
            color = lineColor
        )
        Spacer(Modifier.width(gap))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
        Spacer(Modifier.width(gap))
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(thickness),
            color = lineColor
        )
    }
}
