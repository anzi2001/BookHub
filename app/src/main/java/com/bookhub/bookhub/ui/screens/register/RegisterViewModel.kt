package com.bookhub.bookhub.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookhub.bookhub.api.AuthRepo
import com.bookhub.bookhub.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class RegisterScreenUiState(
    val firstName : String = "",
    val lastName : String = "",
    val dateOfBirth: LocalDateTime? = null,
    val email : String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading : Boolean = false,
    val isRegistered : Boolean = false,
    val error : String? = null,
    val selectedGenres : List<String> = mutableListOf()
)

@HiltViewModel
class RegisterViewModel @Inject constructor (private val authRepo: AuthRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterScreenUiState())
    val uiState : StateFlow<RegisterScreenUiState> = _uiState.asStateFlow()

    fun updateFirstName(name : String){
        _uiState.update { it.copy(firstName = name) }
    }

    fun updateLastName(surname : String){
        _uiState.update { it.copy(lastName = surname) }
    }

    fun updateDateOfBirth(dateOfBirth : LocalDateTime){
        _uiState.update { it.copy(dateOfBirth = dateOfBirth) }
    }

    fun updateEmail(email : String){
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password : String){
        _uiState.update { it.copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword : String){
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun updateSelectedGenres(genre : String, selected : Boolean){
        val selectedGenres = if(selected){
            uiState.value.selectedGenres + genre
        } else{
            uiState.value.selectedGenres.filter { it != genre }
        }
        _uiState.update { it.copy( selectedGenres = selectedGenres) }
    }

    fun register(){
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val user = authRepo.register(
                firstName = uiState.value.firstName,
                lastName = uiState.value.lastName,
                email = uiState.value.email,
                password = uiState.value.password
            )
            when(user){
                is Response.Success -> {
                    _uiState.update { it.copy(isRegistered = true, isLoading = false, error = null) }
                }
                is Response.Error -> {
                    _uiState.update { it.copy(isRegistered = false, isLoading = false, error = user.message) }
                }
            }
        }
    }
}