package com.junnew.features

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.junnew.features.navigation.RootNav
import com.junnew.utils.helper.AppLocaleManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // variable MainViewModel
    private val mainViewModel: MainViewModel by viewModels()
    // trigger login
    private var isAuthenticated = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        AppLocaleManager().getLanguageCode(this)
        isAuthenticated = mainViewModel.isUserAuthenticated()
        setContent { AppRoot(isAuthenticated) }
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