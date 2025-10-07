package com.junnew.features.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import com.junnew.design_system.theme.appColors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.junnew.R
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.dimens
import com.junnew.features.auth.login.components.LoginContent
import com.junnew.features.navigation.AuthGraphRoot
import com.junnew.features.navigation.MainGraphRoot


@Composable
fun LoginScreen(
    nav: NavController? = null,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    val color = MaterialTheme.appColors
    val d = MaterialTheme.dimens
    val text = MaterialTheme.typography
    val shape = MaterialTheme.shapes

    val loginFun = viewModel

    Box(
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
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
       Column {
           Row(
               modifier = Modifier.padding(20.dp).fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.End
           ) {
               Text(stringResource(R.string.txt_dont_have_an_account), color = color.white)
               Spacer(modifier = Modifier.width(10.dp))
               IButton(
                   modifier = Modifier.height(d.extraLarge).background(color.purpleBlueOpa, shape = shape.small).padding(0.dp),
                   onClick = {
                       loginFun.clean()
                       nav?.navigate("register")
                   }) {
                    Text(stringResource(R.string.txt_get_started), style = text.labelMedium)
               }
           }
           Box(
               contentAlignment = Alignment.BottomCenter,
               modifier = Modifier.weight(1f)){
               LoginContent(
                   onNavigateRegister = {
                       nav?.navigate(MainGraphRoot) {
                           popUpTo(AuthGraphRoot) {
                               inclusive = true
                           }
                       }
                   }
               )
           }
       }
    }

}




//@OptIn(ExperimentalSharedTransitionApi::class)
//@Preview()
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}




