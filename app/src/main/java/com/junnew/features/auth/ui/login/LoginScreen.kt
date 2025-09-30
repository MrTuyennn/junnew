package com.junnew.features.auth.ui.login

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import com.junnew.design_system.theme.appColors

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.junnew.features.auth.ui.login.components.BoxContent


@Composable
fun LoginScreen(
    nav: NavController? = null
) {
    val focusManager = LocalFocusManager.current

    val vm: LoginViewModel = hiltViewModel()
    val ui = vm.ui.collectAsState().value

    val color = MaterialTheme.appColors

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        color.purpleBlue,
                        color.purpleBlueOpa
                    )
                )
            )
            .statusBarsPadding()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        BoxContent()
    }

}




@OptIn(ExperimentalSharedTransitionApi::class)
@Preview()
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}




