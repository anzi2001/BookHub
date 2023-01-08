package com.bookhub.bookhub.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.screens.home.SearchBar
import com.bookhub.bookhub.viewmodels.SearchViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(searchViewModel : SearchViewModel = hiltViewModel()) {
    val searchValue by searchViewModel.searchQuery.observeAsState()
    val searchResults by searchViewModel.searchResults.observeAsState()
    val focusRequester = remember{FocusRequester()}


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
        searchViewModel.error.observe(lifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column{
        SearchBar(placeholder = stringResource(R.string.searchFor),
            value = searchValue ?: "",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)){
            searchViewModel.searchTextChanged(it)
            searchViewModel.searchBook(it)
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray))

        searchResults?.let {
            LazyColumn{
                items(it,key = { book ->
                    book.id
                }){ book ->
                    SearchBookItem(modifier = Modifier.animateItemPlacement(), book = book)
                }
            }
        }

    }
}