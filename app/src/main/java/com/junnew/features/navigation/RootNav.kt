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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.junnew.features.main.editProfile.EditProfileScreen
import com.junnew.features.main.products.ProductScreen
import com.junnew.features.navigation.bottom.BottomBar

@Composable
fun RootNav(isAuthenticator: Boolean) {
    val nav = rememberNavController()
    val backStackEntry by nav.currentBackStackEntryAsState()
    val dest = backStackEntry?.destination

    val bottomRoutes = remember {
        setOf(
            HomeRoute::class.qualifiedName!!,
            MapRoute::class.qualifiedName!!,
            ProductRoute::class.qualifiedName!!,
            SettingRoute::class.qualifiedName!!
        )
    }
    val showBottomBar = dest?.hierarchy?.any { it.route in bottomRoutes } == true

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = { if (showBottomBar) BottomBar(navController = nav) }
    ) { inner ->
        Box(Modifier.padding(inner)) {
            NavHost(
                navController = nav,
                startDestination = if (isAuthenticator) MainGraphRoot else AuthGraphRoot,
                enterTransition = { EnterTransition.None },
                exitTransition  = { ExitTransition.None }
            ) {
                authGraph(nav)
                mainGraph(nav)
            }
        }
    }
}
