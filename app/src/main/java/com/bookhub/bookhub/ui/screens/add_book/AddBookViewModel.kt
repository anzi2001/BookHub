package com.bookhub.bookhub.ui.screens.add_book

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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddBookUiState(
    val searchQuery: String = "",
    val bookResults: List<Book>? = null,
    val loading : Boolean = false,
    val error : String? = null,
    val selectedBook : Book? = null
)

@HiltViewModel
class AddBookViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){

    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    private val _bookResults : MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    private val _selectedBook : MutableStateFlow<Book?> = MutableStateFlow(null)
    private val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState : StateFlow<AddBookUiState> = combine(
        _searchQuery, _bookResults, _error, _selectedBook, _loading
    ){ searchQuery, bookResults, error, selectedBook, loading ->
        AddBookUiState(
            searchQuery = searchQuery,
            bookResults = bookResults,
            error = error,
            selectedBook = selectedBook,
            loading = loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = AddBookUiState()
    )

    private var queryJob : Job? = null

    fun updateSearchQuery(query : String){
        _searchQuery.value = query
        getSearchedBooks(_searchQuery.value)
    }

    private fun getSearchedBooks(searchQuery : String){
        queryJob?.cancel()
        queryJob = viewModelScope.launch {
            delay(1000L)
            when(val result = bookRepo.searchBooks(searchQuery)){
                is Response.Success -> _bookResults.value = result.data
                is Response.Error -> _error.value = result.message
            }
        }
    }

    fun updateSelectedBook(book : Book){
        _selectedBook.value = book
    }

}