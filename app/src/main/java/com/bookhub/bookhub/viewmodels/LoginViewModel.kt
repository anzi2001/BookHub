package com.bookhub.bookhub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.AuthRepo
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel(){

    private val _email : MutableLiveData<String> = MutableLiveData()
    val email : LiveData<String> = _email

    private val _password : MutableLiveData<String> = MutableLiveData()
    val password : LiveData<String> = _password

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading : LiveData<Boolean> = _loading

    private val _loginResult : MutableLiveData<Response<User>> = MutableLiveData()
    val loginResult : LiveData<Response<User>> = _loginResult

    fun updateEmail(email : String){
        _email.value = email
    }
    fun updatePassword(password : String){
        _password.value = password
    }

    fun login(){
        viewModelScope.launch {
            if(email.value != null && password.value != null) {
                 _loading.postValue(true);
                _loginResult.postValue(authRepo.login(email.value!!, password.value!!))
                _loading.postValue(false);
            }
        }
    }

}