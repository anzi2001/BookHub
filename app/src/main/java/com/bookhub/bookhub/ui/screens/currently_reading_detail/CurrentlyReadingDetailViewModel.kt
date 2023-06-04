package com.bookhub.bookhub.ui.screens.currently_reading_detail

import androidx.lifecycle.*
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CurrentlyReadingDetailUiState(
    val error : String? = null,
    val loading : Boolean = false,
    val book : Book? = null,
)

@HiltViewModel
class CurrentlyReadingDetailViewModel @Inject constructor(
    private val bookRepo: BookRepo,
    savedStateHandle: SavedStateHandle
    ): ViewModel() {
    private val _book : MutableStateFlow<Book?> = MutableStateFlow(null)
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    private val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState : StateFlow<CurrentlyReadingDetailUiState> = combine(
        _error, _loading, _book
    ){ error, loading, book ->
        CurrentlyReadingDetailUiState(
            error = error,
            loading = loading,
            book = book,
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = CurrentlyReadingDetailUiState()
    )

    init{
        savedStateHandle.get<Int>("id")?.let(::getBook)
    }

    private fun getBook(bookID : Int){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = bookRepo.getBook(bookID)){
                is Response.Success -> _book.value = result.data
                is Response.Error -> _error.value = result.message
            }
            _loading.value = false
        }
    }
}