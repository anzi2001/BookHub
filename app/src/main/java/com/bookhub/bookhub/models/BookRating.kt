package com.bookhub.bookhub.models

import com.squareup.moshi.Json
import java.util.*

data class BookRating (
    val id : Int,
    @Json(name =" user_id")
    val userId : Int,
    @Json(name = "book_id")
    val bookId : Int,
    val rating : Int,
    @Json(name = "created_at")
    val createdAt : Date,
    @Json(name = "updated_at")
    val updatedAt : Date
)