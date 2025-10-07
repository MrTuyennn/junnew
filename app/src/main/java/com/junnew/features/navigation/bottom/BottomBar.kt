package com.junnew.features.navigation.bottom

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.junnew.R
import com.junnew.design_system.theme.AppIcon
import com.junnew.design_system.theme.appColors
import com.junnew.features.navigation.HomeRoute
import com.junnew.features.navigation.MapRoute
import com.junnew.features.navigation.ProductRoute
import com.junnew.features.navigation.SettingRoute

@Composable
fun BottomBar(
    navController: NavHostController,
) {

    val color = MaterialTheme.appColors

    val items = listOf(HomeRoute, MapRoute, ProductRoute, SettingRoute)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    NavigationBar(
        containerColor = color.black
    ) {
        items.forEach { screen ->
            val isSelected = currentDestination
                ?.hierarchy
                ?.any { it.route == screen::class.qualifiedName } == true
            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(
                        text = when (screen) {
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
                        imageVector = when (screen) {
                            HomeRoute -> if (isSelected) AppIcon.HomeFilled else AppIcon.HomeOutlined
                            MapRoute  -> if (isSelected) AppIcon.MapFilled else AppIcon.MapOutlined
                            ProductRoute  -> if (isSelected) AppIcon.ProductFilled else AppIcon.ProductOutlined
                            SettingRoute  -> if (isSelected) AppIcon.SettingFilled else AppIcon.SettingOutlined
                            else -> AppIcon.HomeOutlined
                        },
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = color.white,
                    selectedTextColor = color.white,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(screen) {
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

@Preview
@Composable
fun PreViewBottomBar() {
    BottomBar(
        navController = NavHostController(LocalContext.current)
    )
}