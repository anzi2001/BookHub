package com.bookhub.bookhub.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle
import com.bookhub.bookhub.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navController : NavController, loginViewModel : LoginViewModel = hiltViewModel()){
    val email by loginViewModel.email.observeAsState()
    val password by loginViewModel.password.observeAsState()
    val loading by loginViewModel.loading.observeAsState()

    val isEnabled = !(loading ?: false)

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(Unit){
        loginViewModel.loginResult.observe(lifecycleOwner){
            when(it){
                is Response.Success -> {
                    navController.navigate("mainScreen"){
                        popUpTo("mainScreen"){ inclusive = true }
                    }
                }
                is Response.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column{
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.login), style = TitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterEmailPassword), style = SubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(
                enabled = isEnabled,
                value = email ?: "",
                placeholder = stringResource(R.string.email),
                onTextChanged = loginViewModel::updateEmail,
            )
            HeightSpacer(15.dp)
            BHInputField(
                enabled = isEnabled,
                value = password ?: "",
                placeholder = stringResource(R.string.password),
                visualTransformation = PasswordVisualTransformation(),
                onTextChanged = loginViewModel::updatePassword,
            )
            HeightSpacer(15.dp)
            BHButton(text = stringResource(R.string.continueButton), enabled = isEnabled) {
                loginViewModel.login()
            }
            if(!isEnabled){
                Spacer(Modifier.fillMaxHeight())
                CircularProgressIndicator()
                Spacer(Modifier.fillMaxHeight())
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview(){
    val navController = rememberNavController()
    BookHubTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoginScreen(navController)
        }
    }
}