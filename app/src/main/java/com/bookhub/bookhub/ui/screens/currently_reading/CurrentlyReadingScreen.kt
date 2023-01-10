package com.bookhub.bookhub.ui.screens.currently_reading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.theme.TitleStyle
import com.bookhub.bookhub.viewmodels.CurrentlyReadingViewModel

@Composable
fun CurrentlyReadingScreen(navController: NavController, currentlyReadingViewModel: CurrentlyReadingViewModel = hiltViewModel()) {
    val currentlyReadingList by currentlyReadingViewModel.currentlyReadingBooks.observeAsState()
    Column {
        Text(stringResource(id = R.string.youAreCurrentlyReading), style = TitleStyle,  modifier = Modifier.padding(16.dp))
        currentlyReadingList?.let {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(30.dp)){
                items(it){ book ->
                    CurrentlyReadingBook(
                        book = book,
                        buttonText = stringResource(R.string.read)
                    ){

                    }
                }
            }
        }

    }
}