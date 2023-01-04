package com.bookhub.bookhub.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun SetPasswordScreen(navController: NavController) {
    Column{
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)){
            Text(stringResource(R.string.setPasswordTitle), style = TitleStyle)
            HeightSpacer(height = 30.dp)
            Text(stringResource(R.string.setPasswordSubtitle), style = SubtitleStyle)
            HeightSpacer(height = 30.dp)
            BHInputField(placeholder = stringResource(R.string.password), onTextChanged = {})
            HeightSpacer(height = 20.dp)
            BHInputField(placeholder = stringResource(R.string.confirmPassword), onTextChanged = {})
            UnorderedList(messages = listOf(
                stringResource(R.string.atLeastCharacters),
                stringResource(R.string.smallLetter),
                stringResource(R.string.capitalLetter),
                stringResource(R.string.numberOrSymbol)
            ))
            Spacer(modifier = Modifier.fillMaxHeight())
            BHButton(stringResource(R.string.register)) {
                navController.navigate(BookHubNavigation.SelectGenres.route)
            }
        }
    }

}