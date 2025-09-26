package com.junnew

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.junnew.design_system.constants.LogSystem
import com.junnew.features.auth.ui.login.LoginScreen
import com.junnew.features.auth.ui.register.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppRoot() }
    }
}

@Composable
private fun AppRoot() {
    val nav = rememberNavController()
//    NavHost(navController = nav, startDestination = AuthDestinations.Login) {
//        authGraph(nav) {
//            Log.d(LogSystem.LOG_LEVELS,"Login success -----")
//          //  nav.navigate("home") { popUpTo(AuthDestinations.Login) { inclusive = true } }
//        }
//    }

    NavHost(navController = nav, startDestination = "auth") {
        navigation(startDestination = "login", route = "auth") {
            composable("login") {
                LoginScreen(nav, onSuccess = {
                    Log.d(LogSystem.LOG_LEVELS, "Login success -----")

                }){
                    nav.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
            composable("register") {
                RegisterScreen(nav, onSuccess = {
                    Log.d(LogSystem.LOG_LEVELS, "Register success -----")

                }) {
                    nav.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            }
        }
    }

}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
 fun rememberParentEntry(nav: NavController, parentRoute: String): NavBackStackEntry =
    remember(nav) { nav.getBackStackEntry(parentRoute) }