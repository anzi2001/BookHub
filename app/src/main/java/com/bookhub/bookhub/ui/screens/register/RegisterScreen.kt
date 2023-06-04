@file:OptIn(ExperimentalMaterial3Api::class)

package com.bookhub.bookhub.ui.screens.register

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHDatePickerDialog
import com.bookhub.bookhub.ui.common.BHInputField
import com.bookhub.bookhub.ui.common.BHRefreshingIndicator
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.AuthSubtitleStyle
import com.bookhub.bookhub.ui.theme.BHBUttonColor
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.LoginTitleStyle
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun RegisterScreen(navController : NavController, registerViewModel: RegisterViewModel = hiltViewModel(LocalContext.current as ComponentActivity)) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()

    var showDatePicker by remember{ mutableStateOf(false) }

    val context = LocalContext.current
    if(uiState.error != null){
        LaunchedEffect(uiState.error){
            Toast.makeText(context,uiState.error,Toast.LENGTH_LONG).show()
        }
    }
    if(showDatePicker){
        val datePickerState = rememberDatePickerState()
        BHDatePickerDialog(
            datePickerState = datePickerState,
            onDismissDialog = { showDatePicker = false },
            onConfirmDialog = {
                datePickerState.selectedDateMillis?.let {
                    val time = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                    registerViewModel.updateDateOfBirth(time)
                }
            }
        )
    }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)) {
            Text(stringResource(R.string.register), style = LoginTitleStyle)
            HeightSpacer(10.dp)
            Text(stringResource(R.string.enterData), style = AuthSubtitleStyle)
            HeightSpacer(20.dp)
            BHInputField(
                value = uiState.firstName,
                onTextChanged = registerViewModel::updateFirstName,
                placeholder = stringResource(R.string.firstName)
            )
            HeightSpacer(15.dp)
            BHInputField(
                value = uiState.lastName,
                onTextChanged = registerViewModel::updateLastName,
                placeholder = stringResource(R.string.lastName)
            )
            HeightSpacer(15.dp)
            BHInputField(
                value = uiState.dateOfBirth?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                placeholder = stringResource(R.string.dateOfBirth),
                enabled = false,
                modifier = Modifier.clickable { showDatePicker = true }
            )
            HeightSpacer(15.dp)
            BHInputField(
                value = uiState.email,
                onTextChanged = registerViewModel::updateEmail,
                placeholder = stringResource(R.string.email)
            )
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
    if(uiState.isLoading){
        BHRefreshingIndicator()
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