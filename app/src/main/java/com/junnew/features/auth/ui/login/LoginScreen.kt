package com.junnew.features.auth.ui.login

import android.app.Activity
import android.content.Context
import android.util.Log
import com.junnew.R
import com.junnew.utils.helper.Language
import com.junnew.utils.helper.appLanguages
import com.junnew.design_system.component.button.ButtonType
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.appColors
import com.junnew.rememberParentEntry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.junnew.design_system.constants.LogSystem
import com.junnew.utils.helper.AppLocaleManager


@Composable
fun LoginScreen(
    context: Context,
    nav: NavController,
    onSuccess: () -> Unit,
    onNavigateRegister: () -> Unit,
   // vm: LoginViewModel = hiltViewModel()
) {
//    val ui by vm.ui.collectAsStateWithLifecycle()
    val parentEntry = rememberParentEntry(nav, "auth")
    val vm: LoginViewModel = hiltViewModel(parentEntry)
    val ui = vm.ui.collectAsState().value
    val appLocaleManager = AppLocaleManager()
    //val context = LocalContext.current

    val context = LocalContext.current
    val activity = context as? Activity


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.padding(24.dp)) {
            Text(stringResource(R.string.txt_login), style = TextStyle(color = MaterialTheme.appColors.success, fontSize = 30.sp))
            Spacer(Modifier.height(16.dp))

            Box(modifier = Modifier.padding(10.dp)) {
                LazyColumn {
                    items(appLanguages.size) { index ->
                        LanguageRow(appLanguages[index], appLanguages[index].code == "en") {
                            //onAppLanguageChanged(it.code)
                            Log.e(LogSystem.LOG_LEVELS,"=====> ${it.code}")
                            appLocaleManager.changeLanguage(context, it.code)
                           // activity?.recreate()
                        }
                    }
                }
            }

            OutlinedTextField(
                value = ui.email,
                onValueChange = vm::onEmailChange,
                label = { Text(stringResource(R.string.txt_email)) },
                isError = ui.emailError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (ui.emailError != null) Text(ui.emailError, color = MaterialTheme.colorScheme.error)

            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = ui.password,
                onValueChange = vm::onPasswordChange,
                label = { Text(stringResource(R.string.txt_password)) },
                visualTransformation = PasswordVisualTransformation(),
                isError = ui.passwordError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (ui.passwordError != null) Text(ui.passwordError, color = MaterialTheme.colorScheme.error)

            if (ui.generalError != null) {
                Spacer(Modifier.height(8.dp))
                Text(ui.generalError, color = MaterialTheme.colorScheme.error)
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
            Text(ui.name)
            IButton(buttonType = ButtonType.TONAL) {
                vm.setName("hello")
            }
            Text(
                "Chưa có tài khoản? Đăng ký",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        vm.setName("tuyen")

                        onNavigateRegister()
                    }
                    .padding(8.dp)
            )
        }
    }
}


@Composable
fun LanguageRow(language: Language, isSelected: Boolean, onLanguageClick: (Language) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onLanguageClick(language)
                },
        ) {

            Column {
                Text(
                    text = language.displayLanguage,
                   // style = appTypography.bodyMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = language.code,
                    //style = appTypography.bodySmall,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }

//            if (isSelected) {
//                Icon(imageVector = AppIcons.Check, contentDescription = "Selected")
//            }
        }
        HorizontalDivider(modifier = Modifier.height(1.dp), thickness = 1.dp, color = MaterialTheme.colorScheme.onTertiaryContainer)
    }
}

