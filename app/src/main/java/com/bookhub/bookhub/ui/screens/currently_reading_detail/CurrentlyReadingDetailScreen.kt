package com.bookhub.bookhub.ui.screens.currently_reading_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.screens.currently_reading.CurrentlyReadingBook
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun CurrentlyReadingDetailScreen(bookID : Int?, outerNavController : NavHostController) {
    Column{
        Text(stringResource(R.string.youAreCurrentlyReading), style = TitleStyle)
        CurrentlyReadingBook(book = null, buttonText = stringResource(R.string.change)) {
            outerNavController.popBackStack()
        }
    }
}