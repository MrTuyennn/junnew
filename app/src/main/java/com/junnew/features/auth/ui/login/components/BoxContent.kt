package com.junnew.features.auth.ui.login.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.junnew.R
import com.junnew.design_system.component.box.IBox
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.component.input.EmailTextField
import com.junnew.design_system.component.input.PasswordTextField
import com.junnew.design_system.component.utils.Social
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.utils.constants.LogSystem

@Composable
fun BoxContent() {
    val color = MaterialTheme.appColors
    val text = MaterialTheme.typography
    val shape = MaterialTheme.shapes
    val dimens = MaterialTheme.dimens

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color.white, shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                )
            )
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IBox(height = dimens.extraLarge)
            Text(text = stringResource(R.string.text_welcome_back), style = text.headlineLarge)
            IBox(height = dimens.medium)
            Text(
                text = stringResource(R.string.txt_enter_your_details_below),
                style = text.bodyMedium
            )
            IBox(height = dimens.medium)

            /* on click login */
            IButton(
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                color.purpleBlue,
                                color.pink
                            )
                        ), shape = shape.small
                    )
                    .fillMaxWidth().height(dimens.heightBtn),
                onClick = {
                    Log.e(LogSystem.LOG_LEVELS,"Login")
                }
            ) {
                Text("Sign in", style = text.bodyLarge)
            }
            IBox(height = dimens.large)
            EmailTextField(value = email, onValueChange = { email = it })
            PasswordTextField(value = pass, onValueChange = { pass = it })
            /* on click login */
            Text(stringResource(R.string.txt_forgot_your_password), style = text.labelSmall)
            IBox(height = dimens.extraLarge)
            /* or divider */
            OrDivider(
                text = "Or sign in with",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            /* or divider */
            IBox(height = dimens.large)
            /*Social*/
            Social()
            IBox(height = dimens.extraLarge)
        }
    }
}