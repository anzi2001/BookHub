package com.bookhub.bookhub.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.screens.home.SearchBar
import com.bookhub.bookhub.viewmodels.SearchViewModel

@Composable
fun SearchScreen(searchViewModel : SearchViewModel = viewModel()) {
    val searchResults = searchViewModel.searchResults.observeAsState()
    Column {
        SearchBar(placeholder = stringResource(R.string.searchFor)){
            searchViewModel.searchBook(it)
        }
        searchResults.value?.let {
            LazyColumn{
                items(it){ book ->
                    SearchBookItem(book = book)
                }
            }
        }

    }
}