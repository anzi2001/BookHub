package com.bookhub.bookhub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeScreenUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val toBeRead : List<Book>? = null,
    val currentlyReading : List<Book>? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){
    private val _currentlyReading : MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    private val _toBeRead : MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState = combine(
        _currentlyReading, _toBeRead, _error, _isLoading
    ){ currentlyReading, toBeRead, error, isLoading ->
        HomeScreenUiState(
            isLoading = isLoading,
            error = error,
            toBeRead = toBeRead,
            currentlyReading = currentlyReading
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = HomeScreenUiState()
    )

    init{
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val currentlyReadingJob = viewModelScope.async {
                when(val result = bookRepo.getCurrentlyReadingBooks()){
                    is Response.Success -> _currentlyReading.value = result.data
                    is Response.Error -> _error.value = result.message
                }
            }
            val toBeReadBooks = viewModelScope.async {
                when(val result = bookRepo.getToBeReadBooks()){
                    is Response.Success -> _toBeRead.value = result.data
                    is Response.Error -> _error.value = result.message
                }
            }
            awaitAll(currentlyReadingJob, toBeReadBooks)
            _isLoading.value = false
        }
    }


}