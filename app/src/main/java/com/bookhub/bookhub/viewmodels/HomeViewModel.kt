package com.bookhub.bookhub.viewmodels

import androidx.lifecycle.ViewModel
import com.bookhub.bookhub.api.BookRepo
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel(private val bookRepo: BookRepo) : ViewModel(){



}