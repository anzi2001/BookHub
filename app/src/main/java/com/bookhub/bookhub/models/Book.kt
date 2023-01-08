package com.bookhub.bookhub.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

const val MAX_BOOK_STARS = 5

@JsonClass(generateAdapter = true)
data class Book (
    val id : Int,
    val title : String,
    val image : String? = null, //might be a url, up to debate
    val tags: String,
    val author : String,
    val description : String,
    //@Json(name = "created_at")
    //val createdAt : Date,
    //@Json(name = "updated_at")
    //val updatedAt : Date,
    @Json(name="average_rating")
    val averageRating : Float
)