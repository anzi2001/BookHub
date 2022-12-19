package com.bookhub.bookhub.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun LoginScreen(navController : NavController){
    Column{
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.login), style = TitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterEmailPassword), style = SubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.email))
            HeightSpacer(15.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.password))
            HeightSpacer(15.dp)
            BHButton(text = stringResource(R.string.continueButton)) {
                navController.navigate("mainScreen"){
                    popUpTo("mainScreen"){ inclusive = true }
                }
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