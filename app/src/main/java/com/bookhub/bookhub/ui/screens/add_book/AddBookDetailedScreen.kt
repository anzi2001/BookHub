package com.bookhub.bookhub.ui.screens.add_book

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHRefreshingIndicator
import com.bookhub.bookhub.ui.screens.add_book.components.AddTo
import com.bookhub.bookhub.ui.screens.currently_reading.components.CurrentlyReadingBook

@Composable
fun AddBookDetailedScreen(outerNavController : NavHostController, addBookDetailViewModel: AddBookDetailViewModel = hiltViewModel()) {
    val uiState by addBookDetailViewModel.uiState.collectAsStateWithLifecycle()

    if(uiState.bookAdded){
        LaunchedEffect(Unit){
            outerNavController.navigate(BookHubNavigation.MainScreen.route){
                popUpTo(BookHubNavigation.AddBookDetail.route){
                    inclusive = true
                }
            }
        }
    }

    val context = LocalContext.current
    uiState.error?.let {
        LaunchedEffect(uiState.error) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_LONG).show()
        }
    }
    Column{
        uiState.book?.let{
            Column(modifier = Modifier.weight(1f)){
                CurrentlyReadingBook(book = it)
                AddTo(addBookDetailViewModel)
                Spacer(Modifier.fillMaxHeight())
            }
        }
        BHButton(text = stringResource(R.string.continueButton)) {
            addBookDetailViewModel.addBook()
        }
    }
    if(uiState.loading){
        BHRefreshingIndicator()
    }
}