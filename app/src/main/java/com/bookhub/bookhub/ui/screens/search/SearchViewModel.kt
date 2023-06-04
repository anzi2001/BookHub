package com.bookhub.bookhub.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val error : String? = null,
    val searchQuery : String = "",
    val searchResults : List<Book>? = null,
    val isLoading : Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){

    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    private val _searchResults : MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)

    private var searchJob : Job? = null

    val uiState = combine(
        _isLoading, _error, _searchQuery, _searchResults
    ){ isLoading, error, searchQuery, searchResults ->
        SearchUiState(
            isLoading = isLoading,
            error = error,
            searchQuery = searchQuery,
            searchResults = searchResults
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = SearchUiState()
    )

    fun searchBook(searchQuery : String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            when(val response = bookRepo.searchBooks(searchQuery)){
                is Response.Success<List<Book>> -> _searchResults.value = response.data
                is Response.Error -> _error.value = response.message
            }
        }
    }

    fun searchTextChanged(text : String){
        _searchQuery.value = text
    }
}