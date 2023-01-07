package com.bookhub.bookhub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel(private val bookRepo: BookRepo) : ViewModel(){

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    private val _searchResults : MutableLiveData<List<Book>> = MutableLiveData()
    val searchResults : LiveData<List<Book>> = _searchResults

    private var searchJob : Job? = null

    fun searchBook(searchQuery : String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            when(val response = bookRepo.searchBooks(searchQuery)){
                is Response.Success<List<Book>> -> _searchResults.postValue(response.data)
                is Response.Error -> _error.postValue(response.message)
            }
        }
    }
}