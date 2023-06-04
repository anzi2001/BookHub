package com.bookhub.bookhub.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginUser(
    val email : String,
    val password : String
)

@JsonClass(generateAdapter = true)
data class RegisterUser(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName : String,
    val email : String,
    val password : String,
    @Json(name = "confirm_password")
    val passwordConfirm : String
)

@JsonClass(generateAdapter = true)
data class User (
    val id : Int,
    @Json(name = "first_name")
    val firstName : String,
    @Json(name = "last_name")
    val lastName : String,
    val email : String,
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val user: User,
    val token : String,
)