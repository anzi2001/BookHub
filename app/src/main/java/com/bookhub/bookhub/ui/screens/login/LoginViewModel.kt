package com.bookhub.bookhub.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.AuthRepo
import com.bookhub.bookhub.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginScreenUiState(
    val isLoggedIn : Boolean = false,
    val isLoading : Boolean = false,
    val error : String? = null,
    val email : String = "",
    val password : String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel(){
    private val _uiState = MutableStateFlow(LoginScreenUiState(isLoading = false))
    val uiState : StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun updateEmail(email : String){
        _uiState.update { it.copy(email = email) }
    }
    fun updatePassword(password : String){
        _uiState.update { it.copy(password = password) }
    }

    fun login(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when(val result = authRepo.login(uiState.value.email, uiState.value.password)){
                is Response.Success ->{
                    _uiState.update {
                        it.copy(isLoggedIn = true, isLoading = false)
                    }
                }
                is Response.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}