package com.bookhub.bookhub.models

import com.squareup.moshi.Json
import java.util.*

const val MAX_BOOK_STARS = 5

data class Book (
    val id : Int,
    val title : String,
    val image : String, //might be a url, up to debate
    val tags: String,
    val author : String,
    val description : String,
    @Json(name = "created_at")
    val createdAt : Date,
    @Json(name = "updated_at")
    val updatedAt : Date,
    @Json(name="average_rating")
    val averageRating : Float
)