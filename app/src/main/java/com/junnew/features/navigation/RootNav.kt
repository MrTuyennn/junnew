package com.junnew.features.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.junnew.features.navigation.bottom.BottomBar

@Composable
fun RootNav(isAuthenticator: Boolean) {

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val rootNavHostController = rememberNavController()
    val rootNavBackStackEntry by rootNavHostController.currentBackStackEntryAsState()

    // check show bottom navigation
    when (rootNavBackStackEntry?.destination?.route) {
        HomeRoute::class.qualifiedName -> {
            showBottomBarState.value = true
        }

        MapRoute::class.qualifiedName -> {
            showBottomBarState.value = true
        }

        ProductRoute::class.qualifiedName -> {
            showBottomBarState.value = true
        }

        SettingRoute::class.qualifiedName -> {
            showBottomBarState.value = true
        }

        else -> {
            showBottomBarState.value = false
        }

    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBarState.value) {
                BottomBar(navController = rootNavHostController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .navigationBarsPadding()
        ) {
            NavHost(
                navController = rootNavHostController,
                startDestination = if (isAuthenticator) MainGraphRoot else AuthGraphRoot,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {

                authGraph(
                    rootNavHostController
                )
                mainGraph(
                    rootNavHostController
                )
            }
        }
    }
}