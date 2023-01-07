package com.bookhub.bookhub.models

sealed interface Response<T>{
    class Success<T>(val data: T) : Response<T>()
    class Error<T>(val message: String?) : Response<T>()
}

