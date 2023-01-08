package com.bookhub.bookhub.viewmodels

import androidx.lifecycle.*
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentlyReadingDetailViewModel @Inject constructor(
    private val bookRepo: BookRepo,
    savedStateHandle: SavedStateHandle
    ): ViewModel() {
    private val _book : MutableLiveData<Book> = MutableLiveData()
    val book : LiveData<Book> = _book

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    init{
        savedStateHandle.get<Int>("id")?.let {
            getBook(it)
        }
    }

    private fun getBook(bookID : Int){
        viewModelScope.launch {
            when(val result = bookRepo.getBook(bookID)){
                is Response.Success -> _book.postValue(result.data)
                is Response.Error -> _error.postValue(result.message)
            }
        }
    }
}