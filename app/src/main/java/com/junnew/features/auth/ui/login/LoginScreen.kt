package com.junnew.features.auth.ui.login


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junnew.design_system.component.button.ButtonType
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.appColors


@Composable
fun LoginScreen(
    onSuccess: () -> Unit,
    onNavigateRegister: () -> Unit,
    vm: LoginViewModel = hiltViewModel()
) {
    val ui = vm.ui
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center,) {
        Column(Modifier.padding(24.dp)) {
            Text("Đăng nhập", style = TextStyle(color = MaterialTheme.appColors.success, fontSize = 30.sp))
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = ui.email,
                onValueChange = vm::onEmailChange,
                label = { Text("Email") },
                isError = ui.emailError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (ui.emailError != null) Text(ui.emailError!!, color = MaterialTheme.colorScheme.error)

            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = ui.password,
                onValueChange = vm::onPasswordChange,
                label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(),
                isError = ui.passwordError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (ui.passwordError != null) Text(ui.passwordError!!, color = MaterialTheme.colorScheme.error)

            if (ui.generalError != null) {
                Spacer(Modifier.height(8.dp))
                Text(ui.generalError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { vm.submit(onSuccess) },
                enabled = !ui.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (ui.isLoading) CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                else Text("Đăng nhập")
            }

            Spacer(Modifier.height(12.dp))
            IButton(buttonType = ButtonType.TONAL) {  }
            Text(
                "Chưa có tài khoản? Đăng ký",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onNavigateRegister() }
                    .padding(8.dp)
            )
        }
    }
}
