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
import androidx.navigation.NavHostController
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
    val navDestinationScreens = remember {
        bottomNavItemsList
    }

    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }


    NavigationBar(
        containerColor = color.black
    ) {
        navDestinationScreens.forEachIndexed { index,screen ->
            NavigationBarItem(
                selected = selectedDestination == index,
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
                            HomeRoute -> if (selectedDestination == index) AppIcon.HomeFilled else AppIcon.HomeOutlined
                            MapRoute  -> if (selectedDestination == index) AppIcon.MapFilled else AppIcon.MapOutlined
                            ProductRoute  -> if (selectedDestination == index) AppIcon.ProductFilled else AppIcon.ProductOutlined
                            SettingRoute  -> if (selectedDestination == index) AppIcon.SettingFilled else AppIcon.SettingOutlined
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
                    navController.navigate(route = screen.destination)
                    selectedDestination = index
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