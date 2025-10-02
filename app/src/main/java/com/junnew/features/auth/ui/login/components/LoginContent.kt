package com.junnew.features.auth.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.junnew.design_system.component.input.PasswordTextField
import com.junnew.design_system.component.utils.Social
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.features.auth.ui.login.LoginUIState
import com.junnew.features.auth.ui.login.LoginViewModel

@Composable
fun LoginContent(
    onNavigateRegister: () -> Unit?,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val color = MaterialTheme.appColors
    val text = MaterialTheme.typography
    val shape = MaterialTheme.shapes
    val d = MaterialTheme.dimens

    val loginFun = viewModel
    val loginState: LoginUIState = viewModel.ui.collectAsState().value


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
                text = stringResource(R.string.text_welcome_back),
                style = text.headlineLarge
            )

            IBox(height = d.medium)

            Text(
                text = stringResource(R.string.txt_enter_your_details_below),
                style = text.bodyMedium
            )

            IBox(height = d.medium)


            EmailTextField(
                isError = loginState.emailError != null,
                value = loginState.email,
                onValueChange = { loginFun.onEmailChange(it)  },
                supportingText = loginState.emailError?.asString(context)
            )

            PasswordTextField(
                isError = loginState.passwordError != null,
                value = loginState.password,
                onValueChange = { loginFun.onPasswordChange(it)},
                supportingText = loginState.passwordError?.asString(context)
            )

            IBox(height = d.medium)

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
                   loginFun.submit {
                      // onNavigateRegister()
                   }
                }
            ) {
                Text(text = stringResource(R.string.txt_sign_in), style = text.bodyLarge)
            }

            IBox(height = d.large)


            Spacer(modifier = Modifier.height(d.medium))

            Text(
                text = stringResource(R.string.txt_forgot_your_password),
                style = text.labelSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp)
                    .then(Modifier) // chừa chỗ để add clickable nếu cần
            )

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
