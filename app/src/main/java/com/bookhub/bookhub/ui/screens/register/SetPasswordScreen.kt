package com.bookhub.bookhub.ui.screens.register

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.screens.register.components.UnorderedList
import com.bookhub.bookhub.ui.theme.AuthSubtitleStyle
import com.bookhub.bookhub.ui.theme.AuthorGray
import com.bookhub.bookhub.ui.theme.LoginTitleStyle

@Composable
fun SetPasswordScreen(navController: NavController, registerViewModel: RegisterViewModel = hiltViewModel(LocalContext.current as ComponentActivity)) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        TopDecor(
            showBackButton = true,
            onBackClick = navController::popBackStack
        )
        Column(modifier = Modifier.padding(20.dp)){
            Text(stringResource(R.string.setPasswordTitle), style = LoginTitleStyle)
            HeightSpacer(height = 20.dp)
            Text(stringResource(R.string.setPasswordSubtitle), style = AuthSubtitleStyle)
            HeightSpacer(height = 20.dp)
            BHInputField(
                value = uiState.password,
                placeholder = stringResource(R.string.password),
                onTextChanged = registerViewModel::updatePassword
            )
            HeightSpacer(height = 20.dp)
            BHInputField(
                value = uiState.confirmPassword,
                placeholder = stringResource(R.string.confirmPassword),
                onTextChanged = registerViewModel::updateConfirmPassword
            )
            HeightSpacer(height = 15.dp)
            UnorderedList(
                color = AuthorGray,
                messages = listOf(
                    stringResource(R.string.atLeastCharacters),
                    stringResource(R.string.smallLetter),
                    stringResource(R.string.capitalLetter),
                    stringResource(R.string.numberOrSymbol)
            ))
            HeightSpacer(height = 15.dp)
            Spacer(modifier = Modifier.fillMaxHeight())
            BHButton(stringResource(R.string.continueButton)) {
                navController.navigate(BookHubNavigation.SelectGenres.route)
            }
        }
    }

}