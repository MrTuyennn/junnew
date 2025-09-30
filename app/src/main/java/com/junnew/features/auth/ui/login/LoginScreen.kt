package com.junnew.features.auth.ui.login

import com.junnew.R
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import com.junnew.design_system.theme.appColors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(
    nav: NavController? = null
) {
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
    ) {
        BoxContent()
    }

}


@Composable
fun BoxContent() {
    val color = MaterialTheme.appColors
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        .background(color.white, shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
        ))
        .height(100.dp)
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Text(text = stringResource(R.string.text_welcome_back))
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview()
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}




