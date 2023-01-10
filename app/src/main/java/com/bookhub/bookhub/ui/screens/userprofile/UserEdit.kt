package com.bookhub.bookhub.ui.screens.userprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.common.*
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun UserEdit(){
    /*
    val isEnabled = true
    val firstName = String
    val lastName = String
    val dateOfBirth = String
    val email = String
    Column {
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.login), style = TitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterEmailPassword), style = SubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(
                enabled = isEnabled,
                value = firstName ?: "",
                placeholder = stringResource(R.string.firstName),
                onTextChanged = loginViewModel::updateEmail,
            )
            HeightSpacer(15.dp)
            BHInputField(
                enabled = isEnabled,
                value = lastName ?: "",
                placeholder = stringResource(R.string.lastName),
                visualTransformation = PasswordVisualTransformation(),
                onTextChanged = loginViewModel::updatePassword,
            )
            HeightSpacer(15.dp)
            BHInputField(
                enabled = isEnabled,
                value = dateOfBirth ?: "",
                placeholder = stringResource(R.string.dateOfBirth),
                visualTransformation = PasswordVisualTransformation(),
                onTextChanged = loginViewModel::updatePassword,
            )
            HeightSpacer(15.dp)
            BHInputField(
                enabled = isEnabled,
                value = email ?: "",
                placeholder = stringResource(R.string.email),
                visualTransformation = PasswordVisualTransformation(),
                onTextChanged = loginViewModel::updatePassword,
            )
            BHButtonDark("DELETE ACCOUNT", enabled = isEnabled) {
            }
            BHButton("Save", enabled = isEnabled) {
            }
            if(!isEnabled){
                Spacer(Modifier.fillMaxHeight())
                CircularProgressIndicator()
                Spacer(Modifier.fillMaxHeight())
            }
        }
    }
     */
}