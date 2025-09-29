package com.junnew.features.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.junnew.features.auth.ui.login.LoginScreen
import com.junnew.features.auth.ui.register.RegisterScreen
object AuthDestinations {
    const val Login = "auth/login"
    const val Register = "auth/register"
}

fun NavGraphBuilder.authGraph(nav: NavController, onAuthSuccess: () -> Unit) {
//    composable(AuthDestinations.Login) {
//        LoginScreen(
//            c
//            nav = nav,
//            onSuccess = onAuthSuccess,
//            onNavigateRegister = { nav.navigate(AuthDestinations.Register) }
//        )
//    }
//    composable(AuthDestinations.Register) {
//        RegisterScreen(
//            nav = nav,
//            onSuccess = onAuthSuccess,
//            onNavigateLogin = { nav.popBackStack() /* quay lại login */ }
//        )
//    }
}