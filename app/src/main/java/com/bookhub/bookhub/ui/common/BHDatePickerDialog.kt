package com.bookhub.bookhub.ui.common

import android.widget.DatePicker
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.stringResource
import com.bookhub.bookhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BHDatePickerDialog(datePickerState : DatePickerState, onDismissDialog: () -> Unit, onConfirmDialog : () -> Unit) {
    val dismissDialog by rememberUpdatedState(onDismissDialog)
    val confirmDialog by rememberUpdatedState(onConfirmDialog)
    DatePickerDialog(
        onDismissRequest = dismissDialog,
        confirmButton = {
            TextButton(onClick = {
                confirmDialog()
                dismissDialog()
            }){
                Text(stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = dismissDialog) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}