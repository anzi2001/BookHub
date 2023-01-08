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
class AddBookViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){

    private val _searchQuery : MutableLiveData<String> = MutableLiveData()
    val searchQuery : LiveData<String> = _searchQuery

    private val _bookResults : MutableLiveData<List<Book>> = MutableLiveData()
    val bookResults : LiveData<List<Book>> = _bookResults

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    private var queryJob : Job? = null

    fun updateSearchQuery(query : String){
        _searchQuery.value = query
        searchQuery.value?.let{
            getSearchedBooks(it)
        }
    }

    private fun getSearchedBooks(searchQuery : String){
        queryJob?.cancel()
        queryJob = viewModelScope.launch {
            delay(1000L)
            when(val result = bookRepo.searchBooks(searchQuery)){
                is Response.Success -> _bookResults.postValue(result.data)
                is Response.Error -> _error.postValue(result.message)
            }
        }
    }

}