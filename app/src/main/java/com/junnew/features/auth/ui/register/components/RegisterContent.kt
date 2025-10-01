package com.junnew.features.auth.ui.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.junnew.design_system.component.input.InputUserName
import com.junnew.design_system.component.input.PasswordTextField
import com.junnew.design_system.component.utils.Social
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.features.auth.ui.login.components.OrDivider

@Composable
fun RegisterContent() {

    val color = MaterialTheme.appColors
    val text = MaterialTheme.typography
    val shape = MaterialTheme.shapes
    val d = MaterialTheme.dimens

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = color.white,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IBox(height = d.extraLarge)

            Text(
                text = stringResource(R.string.txt_get_started_free),
                style = text.headlineLarge
            )

            IBox(height = d.medium)

            Text(
                text = stringResource(R.string.txt_free_forever_no_credit),
                style = text.bodyMedium
            )

            EmailTextField(
                value = email,
                onValueChange = { email = it },
            )

            InputUserName(
                value = userName,
                onValueChange = { userName = it },
            )

            PasswordTextField(
                value = password,
                onValueChange = { password = it},
            )

            // Nút "Sign in"
            IButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(d.heightBtn)
                    .background(
                        Brush.horizontalGradient(listOf(color.purpleBlue, color.pink)),
                        shape = shape.small
                    ),
                onClick = {
                    //onNavigateRegister()
                }
            ) {
                Text(text = stringResource(R.string.txt_get_started_free), style = text.bodyLarge)
            }

            IBox(height = d.extraLarge)

            OrDivider(
                text = stringResource(R.string.txt_or_sign_in_with),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            IBox(height = d.large)

            Social()

            IBox(height = d.extraLarge)
        }
    }
}