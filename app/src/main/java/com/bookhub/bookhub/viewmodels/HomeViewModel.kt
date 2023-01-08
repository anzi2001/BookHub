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
class HomeViewModel @Inject constructor(private val bookRepo: BookRepo) : ViewModel(){

    private val _currentlyReading : MutableLiveData<List<Book>> = MutableLiveData()
    val currentlyReading : LiveData<List<Book>> = _currentlyReading

    private val _toBeRead : MutableLiveData<List<Book>> = MutableLiveData()
    val toBeRead : LiveData<List<Book>> = _toBeRead

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    init{
        viewModelScope.launch {
            when(val result = bookRepo.getCurrentlyReadingBooks()){
                is Response.Success -> _currentlyReading.postValue(result.data)
                is Response.Error -> _error.postValue(result.message)
            }

            when(val result = bookRepo.getToBeReadBooks()){
                is Response.Success -> _toBeRead.postValue(result.data)
                is Response.Error -> _error.postValue(result.message)
            }
        }
    }


}