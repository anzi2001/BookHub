package com.bookhub.bookhub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentlyReadingViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel() {

    private val _currentlyReadingBooks : MutableLiveData<List<Book>> = MutableLiveData()
    val currentlyReadingBooks : LiveData<List<Book>> = _currentlyReadingBooks

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    init{
        getCurentlyReadingBooks()
    }

    private fun getCurentlyReadingBooks(){
        viewModelScope.launch {
            when(val result = bookRepo.getCurrentlyReadingBooks()){
                is Response.Success -> _currentlyReadingBooks.postValue(result.data)
                is Response.Error -> _error.postValue(result.message)
            }
        }
    }
}