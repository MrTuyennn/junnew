package com.junnew.features.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.junnew.features.auth.login.LoginScreen
import com.junnew.features.auth.register.RegisterScreen
import kotlinx.serialization.Serializable

@Serializable data object AuthGraphRoot
@Serializable data object LoginRoute
@Serializable data object RegisterRoute

fun NavGraphBuilder.authGraph(nav: NavController) {
    navigation<AuthGraphRoot>(startDestination = LoginRoute){
        composable<LoginRoute> {
            LoginScreen(nav = nav)
        }
        composable<RegisterRoute> {
            RegisterScreen(
                nav = nav,
                onSuccess = {},
                onNavigateLogin = { nav.popBackStack() }
            )
        }
    }
}