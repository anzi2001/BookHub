package com.bookhub.bookhub.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.BHRefreshingIndicator
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.AuthSubtitleStyle
import com.bookhub.bookhub.ui.theme.BHBUttonColor
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.LoginTitleStyle
import com.bookhub.bookhub.ui.theme.SubtitleStyle

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    val isEnabled = !uiState.isLoading
    val context = LocalContext.current

    if (uiState.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate("mainScreen") {
                popUpTo("mainScreen") { inclusive = true }
            }
        }
    }
    Column {
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.login), style = LoginTitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterEmailPassword), style = AuthSubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(
                enabled = isEnabled,
                value = uiState.email,
                placeholder = stringResource(R.string.email),
                onTextChanged = loginViewModel::updateEmail,
            )
            HeightSpacer(15.dp)
            BHInputField(
                enabled = isEnabled,
                value = uiState.password,
                placeholder = stringResource(R.string.password),
                visualTransformation = PasswordVisualTransformation(),
                onTextChanged = loginViewModel::updatePassword,
            )
            HeightSpacer(25.dp)
            BHButton(text = stringResource(R.string.continueButton), enabled = isEnabled) {
                loginViewModel.login()
            }
            HeightSpacer(height = 10.dp)
            Text(
                text = stringResource(R.string.register),
                style = SubtitleStyle,
                color = BHBUttonColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(BookHubNavigation.Register.route)
                    }
            )
        }
    }
    if (uiState.isLoading) {
        BHRefreshingIndicator()
    }

    uiState.error?.let {
        LaunchedEffect(uiState.error) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    val navController = rememberNavController()
    BookHubTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoginScreen(navController)
        }
    }
}