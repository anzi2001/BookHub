package com.bookhub.bookhub.ui.screens.add_book

import androidx.lifecycle.*
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.BookStatus
import com.bookhub.bookhub.models.BookStatusEnums
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddBookDetailUiState(
    val book : Book? = null,
    val error : String? = null,
    val bookStatus : BookStatus? = null,
    val loading : Boolean = false,
    val bookAdded : Boolean = false,
)

@HiltViewModel
class AddBookDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle,private val bookRepo: BookRepo) : ViewModel() {
    private val _book : MutableStateFlow<Book?> = MutableStateFlow(null)
    private val _error = MutableStateFlow<String?>(null)
    private val _bookState : MutableStateFlow<BookStatus> = MutableStateFlow(BookStatus(BookStatusEnums.TOBEREAD))
    private val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _bookAdded : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState : StateFlow<AddBookDetailUiState> = combine(
        _book, _error, _bookState, _loading
    ){ book, error, bookState, loading ->
        AddBookDetailUiState(
            book = book,
            error = error,
            bookStatus = bookState,
            loading = loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = AddBookDetailUiState()
    )

    init{
        _loading.value = true
        viewModelScope.launch {
            val bookID : Int = savedStateHandle["id"] ?: return@launch
            when(val result = bookRepo.getBook(bookID)){
                is Response.Success -> _book.value = result.data
                is Response.Error -> _error.value = result.message
            }
            _loading.value = false
        }
    }

    fun updateBookState(bookStatusEnums: BookStatusEnums){
        _bookState.value = BookStatus(bookStatusEnums)
    }

    fun addBook(){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            _book.value?.let {
                val response = bookRepo.addBook(it)
                if(response !is Response.Success){
                    _loading.value = false
                    return@launch
                }
                val updateStatusResponse = bookRepo.updateBookStatus(response.data.id, _bookState.value)
                if(updateStatusResponse !is Response.Success){
                    _loading.value = false
                    return@launch
                }
                _loading.value = false
                _bookAdded.value = true
            }
        }
    }
}