package com.junnew

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.junnew.design_system.constants.LogSystem
import com.junnew.features.auth.navigation.AuthDestinations
import com.junnew.features.auth.navigation.authGraph
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
    NavHost(navController = nav, startDestination = AuthDestinations.Login) {
        authGraph(nav) {
            Log.d(LogSystem.LOG_LEVELS,"Login success -----")
          //  nav.navigate("home") { popUpTo(AuthDestinations.Login) { inclusive = true } }
        }
    }
}