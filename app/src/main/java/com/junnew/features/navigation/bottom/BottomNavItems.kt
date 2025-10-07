package com.junnew.features.navigation.bottom

import androidx.compose.ui.graphics.vector.ImageVector
import com.junnew.R
import com.junnew.design_system.theme.AppIcon
import com.junnew.features.navigation.HomeRoute
import com.junnew.features.navigation.MapRoute
import com.junnew.features.navigation.ProductRoute
import com.junnew.features.navigation.SettingRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavItem<T>(
    val destination: T,
    val titleRes: Int,
    @Contextual val selectedIcon: ImageVector,
    @Contextual val unselectedIcon: ImageVector
) {
    @Serializable
    data object Home : BottomNavItem<HomeRoute>(
        HomeRoute,
        R.string.txt_home,
        AppIcon.HomeFilled,
        AppIcon.HomeOutlined
    )

    @Serializable
    data object Map : BottomNavItem<MapRoute>(
        MapRoute,
        R.string.txt_map,
        AppIcon.MapFilled,
        AppIcon.MapOutlined
    )

    @Serializable
    data object Product : BottomNavItem<ProductRoute>(
        ProductRoute,
        R.string.txt_product,
        AppIcon.ProductFilled,
        AppIcon.ProductOutlined
    )

    @Serializable
    data object Setting : BottomNavItem<SettingRoute>(
        SettingRoute,
        R.string.txt_setting,
        AppIcon.SettingFilled,
        AppIcon.SettingOutlined
    )
}

val bottomNavItemsList = listOf(
    BottomNavItem.Home,
    BottomNavItem.Map,
    BottomNavItem.Product,
    BottomNavItem.Setting
)