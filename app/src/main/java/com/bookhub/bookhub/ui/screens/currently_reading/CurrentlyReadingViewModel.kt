package com.bookhub.bookhub.ui.screens.currently_reading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CurrentlyReadingScreenUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val currentlyReadingBooks : List<Book>? = null
)

@HiltViewModel
class CurrentlyReadingViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel() {
    private val _currentlyReadingBooks : MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState = combine(
        _currentlyReadingBooks, _error, _isLoading
    ){ currentlyReadingBooks, error, isLoading ->
        CurrentlyReadingScreenUiState(
            isLoading = isLoading,
            currentlyReadingBooks = currentlyReadingBooks,
            error = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = CurrentlyReadingScreenUiState()
    )

    init{
        getCurrentlyReadingBooks()
    }

    private fun getCurrentlyReadingBooks(){
        viewModelScope.launch {
            _isLoading.value = true
            when(val result = bookRepo.getCurrentlyReadingBooks()){
                is Response.Success -> _currentlyReadingBooks.value = result.data
                is Response.Error -> _error.value = result.message
            }
            _isLoading.value = false
        }
    }
}