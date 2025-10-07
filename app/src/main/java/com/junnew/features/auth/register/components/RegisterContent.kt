package com.junnew.features.auth.register.components

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junnew.R
import com.junnew.design_system.component.box.IBox
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.component.input.EmailTextField
import com.junnew.design_system.component.input.InputUserName
import com.junnew.design_system.component.input.PasswordTextField
import com.junnew.design_system.component.utils.Social
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.features.auth.login.components.OrDivider
import com.junnew.features.auth.register.RegisterUIState
import com.junnew.features.auth.register.RegisterViewModel

@Composable
fun RegisterContent(
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val color = MaterialTheme.appColors
    val text = MaterialTheme.typography
    val shape = MaterialTheme.shapes
    val d = MaterialTheme.dimens

    val registerFun = viewModel
    val registerState: RegisterUIState = viewModel.ui.collectAsState().value

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
                isError = registerState.emailError != null,
                value = registerState.email,
                onValueChange = { registerFun.onEmailChange(it) },
                supportingText = registerState.emailError?.asString(context)
            )

            InputUserName(
                isError = registerState.nameError != null,
                value = registerState.name,
                onValueChange = { registerFun.onNameChange(it) },
                supportingText = registerState.nameError?.asString(context)
            )

            PasswordTextField(
                isError = registerState.passwordError != null,
                value = registerState.password,
                onValueChange = { registerFun.onPasswordChange(it)},
                supportingText = registerState.passwordError?.asString(context)
            )

            IBox(height = d.extraLarge)

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
                    registerFun.submit {
                        // Xử lý khi đăng ký thành công
                    }
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
        }
    }
}