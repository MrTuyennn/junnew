package com.junnew.features.auth.ui.register


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.junnew.features.auth.ui.login.LoginViewModel
import com.junnew.rememberParentEntry

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No Activity found in context chain.")
}
@Composable
fun RegisterScreen(
    nav: NavController,
    onSuccess: () -> Unit,
    onNavigateLogin: () -> Unit,
    //vm: () -> Unit = hiltViewModel(),
//    vmAuth: LoginViewModel= hiltViewModel()
) {
//    val activity = LocalContext.current.findActivity()
//    val vmAuth: LoginViewModel = hiltViewModel(activity as ViewModelStoreOwner)
//    val ui = vm.ui
//    val login by vmAuth.ui.collectAsStateWithLifecycle()
    val parentEntry = rememberParentEntry(nav, "auth")
    val vm: LoginViewModel = hiltViewModel(parentEntry)
    val ui = vm.ui.collectAsState().value
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.padding(24.dp)) {
            Text("Đăng ký", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

//            OutlinedTextField(
//                value = ui.name,
//                onValueChange = vm::onNameChange,
//                label = { Text("Họ tên") },
//                isError = ui.nameError != null,
//                modifier = Modifier.fillMaxWidth()
//            )
//            if (ui.nameError != null) Text(ui.nameError!!, color = MaterialTheme.colorScheme.error)
//
//            Spacer(Modifier.height(8.dp))
//            OutlinedTextField(
//                value = ui.email,
//                onValueChange = vm::onEmailChange,
//                label = { Text("Email") },
//                isError = ui.emailError != null,
//                modifier = Modifier.fillMaxWidth()
//            )
//            if (ui.emailError != null) Text(ui.emailError!!, color = MaterialTheme.colorScheme.error)
//
//            Spacer(Modifier.height(8.dp))
//            Text(login.name ?: "tuyen")
//            OutlinedTextField(
//                value = ui.password,
//                onValueChange = vm::onPasswordChange,
//                label = { Text("Mật khẩu") },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = ui.passwordError != null,
//                modifier = Modifier.fillMaxWidth()
//            )
//            if (ui.passwordError != null) Text(ui.passwordError!!, color = MaterialTheme.colorScheme.error)
//
//            if (ui.generalError != null) {
//                Spacer(Modifier.height(8.dp))
//                Text(ui.generalError!!, color = MaterialTheme.colorScheme.error)
//            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { vm.submit(onSuccess) },
                enabled = !ui.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (ui.isLoading) CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                else Text("Tạo tài khoản")
            }

            Text(ui.name)

            Spacer(Modifier.height(12.dp))
            Text(
                "Đã có tài khoản? Đăng nhập",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        vm.setName("clean")
                        onNavigateLogin() }
                    .padding(8.dp)
            )
        }
    }
}