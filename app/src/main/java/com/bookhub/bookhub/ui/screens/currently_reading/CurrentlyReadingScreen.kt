package com.bookhub.bookhub.ui.screens.currently_reading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun CurrentlyReadingScreen() {
    val itemList = listOf<String>()
    Column {
        Text(stringResource(id = R.string.youAreCurrentlyReading), style = TitleStyle)
        LazyColumn{
            items(itemList){
                CurrentlyReadingBook()
            }
        }
    }
}