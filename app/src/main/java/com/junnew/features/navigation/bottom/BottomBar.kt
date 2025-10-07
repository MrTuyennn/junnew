package com.junnew.features.navigation.bottom

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.junnew.R
import com.junnew.design_system.theme.AppIcon
import com.junnew.features.navigation.HomeRoute
import com.junnew.features.navigation.MapRoute
import com.junnew.features.navigation.ProductRoute
import com.junnew.features.navigation.SettingRoute
import kotlin.toString

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navDestinationScreens = remember {
        bottomNavItemsList
    }

    NavigationBar {

        navDestinationScreens.forEach { screen ->
            val isSelected = currentDestination?.let { it.route == screen.destination.toString() } == true

            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(
                        text = when (screen.destination) {
                            HomeRoute  -> stringResource(R.string.txt_home)
                            MapRoute -> stringResource(R.string.txt_map)
                            ProductRoute -> stringResource(R.string.txt_product)
                            SettingRoute -> stringResource(R.string.txt_setting)
                            else -> ""
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = {
                    Icon(
                        imageVector = when (screen.destination) {
                            HomeRoute -> if (isSelected) AppIcon.HomeFilled else AppIcon.HomeOutlined
                            MapRoute  -> if (isSelected) AppIcon.MapFilled else AppIcon.MapOutlined
                            ProductRoute  -> if (isSelected) AppIcon.ProductFilled else AppIcon.ProductOutlined
                            SettingRoute  -> if (isSelected) AppIcon.SettingFilled else AppIcon.SettingOutlined
                            else -> AppIcon.HomeOutlined
                        },
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(screen.destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}