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
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    private val _searchResults : MutableLiveData<List<Book>> = MutableLiveData()
    val searchResults : LiveData<List<Book>> = _searchResults

    private val _searchQuery : MutableLiveData<String> = MutableLiveData()
    val searchQuery : LiveData<String> = _searchQuery


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

    fun searchTextChanged(text : String){
        _searchQuery.value = text
    }
}