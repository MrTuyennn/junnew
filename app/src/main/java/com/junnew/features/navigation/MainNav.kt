package com.junnew.features.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.junnew.features.main.home.HomeScreen
import com.junnew.features.main.map.MapScreen
import com.junnew.features.main.products.ProductScreen
import com.junnew.features.main.setting.SettingScreen
import kotlinx.serialization.Serializable

@Serializable data object MainGraphRoot
@Serializable data object HomeRoute
@Serializable data object MapRoute
@Serializable data object ProductRoute
@Serializable data object SettingRoute

fun NavGraphBuilder.mainGraph(nav: NavController) {
    navigation<MainGraphRoot>(startDestination = HomeRoute){
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<MapRoute> {
            MapScreen()
        }
        composable<ProductRoute> {
            ProductScreen()
        }
        composable<SettingRoute> {
            SettingScreen()
        }
    }
}