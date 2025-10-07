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
import com.junnew.features.auth.login.LoginScreen
import com.junnew.features.auth.register.RegisterScreen
import com.junnew.features.navigation.RootNav
import com.junnew.utils.helper.AppLocaleManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        AppLocaleManager().getLanguageCode(this)
        setContent { AppRoot(false) }
    }
}

@Composable
private fun AppRoot(isAuthenticated: Boolean) {
    RootNav(isAuthenticated)
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun rememberParentEntry(nav: NavController, parentRoute: String): NavBackStackEntry =
    remember(nav) { nav.getBackStackEntry(parentRoute) }