package com.bookhub.bookhub.ui.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.screens.login.LoginScreen
import com.bookhub.bookhub.ui.theme.BHBUttonColor
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun RegisterScreen(navController : NavController) {
    Column{
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.register), style = TitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterData), style = SubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.firstName))
            HeightSpacer(15.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.lastName))
            HeightSpacer(15.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.dateOfBirth))
            HeightSpacer(15.dp)
            BHInputField(onTextChanged = {}, placeholder = stringResource(R.string.email))
            HeightSpacer(15.dp)
            BHButton(text = stringResource(R.string.continueButton)) {
                navController.navigate(BookHubNavigation.SetPassword.route){
                    popUpTo(BookHubNavigation.SetPassword.route){ inclusive = true }
                }
            }
            HeightSpacer(20.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(stringResource(R.string.alreadyHaveAccount))
                Text(stringResource(R.string.login), color = BHBUttonColor, modifier = Modifier.clickable {
                    navController.navigate(BookHubNavigation.Login.route)
                })
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    val navController = rememberNavController()
    BookHubTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RegisterScreen(navController)
        }
    }
}