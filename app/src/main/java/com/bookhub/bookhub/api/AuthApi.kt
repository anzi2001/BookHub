package com.bookhub.bookhub.api

import com.bookhub.bookhub.models.LoginResponse
import com.bookhub.bookhub.models.LoginUser
import com.bookhub.bookhub.models.RegisterUser
import com.bookhub.bookhub.models.User
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/login")
    suspend fun login(@Body user : LoginUser) : LoginResponse

    @POST("/register")
    suspend fun register(@Body user : RegisterUser) : User

    @POST("/logout")
    suspend fun logout() : JSONObject
}