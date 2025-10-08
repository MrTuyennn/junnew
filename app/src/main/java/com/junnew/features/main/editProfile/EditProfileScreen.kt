package com.junnew.features.main.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.junnew.R
import com.junnew.core.domain.entity.User
import com.junnew.design_system.component.button.IButton
import com.junnew.design_system.theme.appColors
import com.junnew.design_system.theme.dimens
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    nav: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
){
    val d = MaterialTheme.dimens
    val color = MaterialTheme.appColors
    val shape = MaterialTheme.shapes
    val scope = rememberCoroutineScope()


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Yellow)){
        Text(viewModel.currentUser.collectAsState().value.userName)
        IButton(
            modifier = Modifier.height(d.extraLarge).background(color.purpleBlueOpa, shape = shape.small).padding(0.dp),
            onClick = {
                scope.launch {
                    viewModel.updateUser(User(userName = "tuyen", phone = 23467238, email = "emaiu"))
                    nav.popBackStack()
                }

            }) {
            Text(stringResource(R.string.txt_get_started))
        }
    }
}