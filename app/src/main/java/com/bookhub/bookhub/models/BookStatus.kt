package com.bookhub.bookhub.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

enum class BookStatusEnums(value : String){
    @Json(name = "reading")
    READING("reading"),
    @Json(name="To be read")
    TOBEREAD("To be read")
}


@JsonClass(generateAdapter = true)
data class BookStatus(
    val status : BookStatusEnums
)