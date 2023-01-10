package com.bookhub.bookhub.ui.screens.add_book

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.AuthorStyle
import com.bookhub.bookhub.ui.theme.TitleStyle
import com.bookhub.bookhub.viewmodels.AddBookViewModel

@Composable
fun AddBookScreen(outerNavController : NavHostController, addBookViewModel: AddBookViewModel = hiltViewModel()){
    val bookResults by addBookViewModel.bookResults.observeAsState()
    val searchQuery by addBookViewModel.searchQuery.observeAsState()
    val selectedBook by addBookViewModel.selectedBook.observeAsState()
    val context = LocalContext.current

    Column{
        TopDecor()
        Column(modifier = Modifier.padding(horizontal = 16.dp)){
            HeightSpacer(height = 10.dp)
            Text(stringResource(R.string.addBook), style = TitleStyle)
            HeightSpacer(height = 20.dp)
            Text(stringResource(R.string.whatToAdd), style = AuthorStyle)
            HeightSpacer(height = 20.dp)
            AutoCompleteSearch(modifier = Modifier.fillMaxWidth(),
                searchValue = searchQuery ?: "",
                searchResults = bookResults ?: listOf(),
                onValueChange = addBookViewModel::updateSearchQuery,
                onBookSelected = addBookViewModel::updateSelectedBook
            )
            Spacer(modifier = Modifier.fillMaxHeight())
            BHButton(stringResource(R.string.continueButton)) {
                if(selectedBook == null){
                    Toast.makeText(context, context.getString(R.string.selectBook), Toast.LENGTH_LONG).show()
                }
                else{
                    outerNavController.navigate("${BookHubNavigation.AddBookDetail.route}${selectedBook!!.id}")
                }
            }
        }

    }

}