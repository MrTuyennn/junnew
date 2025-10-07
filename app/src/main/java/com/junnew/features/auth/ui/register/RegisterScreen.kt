package com.junnew.features.auth.ui.register


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.junnew.R
import com.junnew.design_system.component.button.ButtonType
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import com.junnew.features.auth.ui.login.LoginViewModel
import com.junnew.features.auth.ui.login.components.LoginContent
import com.junnew.features.auth.ui.register.components.RegisterContent
import com.junnew.rememberParentEntry

@Composable
fun RegisterScreen(
    nav: NavController,
    onSuccess: () -> Unit,
    onNavigateLogin: () -> Unit,
    //vm: () -> Unit = hiltViewModel(),
    //vmAuth: LoginViewModel= hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val parentEntry = rememberParentEntry(nav, "auth")
    val vm: LoginViewModel = hiltViewModel(parentEntry)
    val ui = vm.ui.collectAsState().value


    val color = MaterialTheme.appColors
    val shape = MaterialTheme.shapes
    val d = MaterialTheme.dimens
    val text = MaterialTheme.typography

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
            .statusBarsPadding()
            .navigationBarsPadding()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {

       Column {
           Row(modifier = Modifier.padding(20.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {

               // icon back
               IconButton(onClick = {
                   nav.popBackStack()
               }) {
                   Icon(
                       imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                       contentDescription = "back",
                       tint = color.white
                   )
               }

               // support text
               Box(
                   contentAlignment = Alignment.CenterEnd,
                   modifier = Modifier.weight(1f)) {
                   Row(
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Text(stringResource(R.string.txt_already_have_an_account), color = color.white)
                       Spacer(modifier = Modifier.width(10.dp))
                       IButton(
                           modifier = Modifier.height(d.extraLarge).background(color.purpleBlueOpa, shape = shape.small).padding(0.dp),
                           onClick = {
                               nav.popBackStack()
                           }) {
                           Text(stringResource(R.string.txt_sign_in), style = text.labelMedium)
                       }
                   }
               }
           }
           Box(
               contentAlignment = Alignment.BottomCenter,
               modifier = Modifier.weight(1f)){
               RegisterContent()
           }
       }

    }
}