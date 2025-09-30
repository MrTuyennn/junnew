package com.junnew

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.junnew.utils.constants.LogSystem
import com.junnew.features.auth.ui.login.LoginScreen
import com.junnew.features.auth.ui.register.RegisterScreen
import com.junnew.utils.helper.AppLocaleManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        AppLocaleManager().getLanguageCode(this)
        setContent { AppRoot(this) }
    }
}

@Composable
private fun AppRoot(context: Context) {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "auth") {

        /// auth ///
        navigation(startDestination = "login", route = "auth") {
            composable("login") {
                LoginScreen(
                    nav = nav,
                )
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

        /// main
    }

}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun rememberParentEntry(nav: NavController, parentRoute: String): NavBackStackEntry =
    remember(nav) { nav.getBackStackEntry(parentRoute) }