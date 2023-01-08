package com.bookhub.bookhub.api

import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.BookRating
import com.bookhub.bookhub.models.BookStatus
import org.json.JSONObject
import retrofit2.http.*

interface BookApi {
    @GET("books/")
    suspend fun allBooks() : List<Book>

    @GET("books/{id}")
    suspend fun book(@Path("id") id : Int) : Book

    @GET("books/{id}/ratings")
    suspend fun bookRatings(@Path("id") id : Int) : List<BookRating>

    @GET("books/search/{query}")
    suspend fun searchBooks(@Path("query") query : String) : List<Book>

    @POST("books")
    suspend fun addBook(@Body book : Book) : Book

    @PUT("books/{id}")
    suspend fun updateBook(@Path("id") id : Int, @Body book :Book) : Book

    @DELETE("books/{id}")
    suspend fun deleteBook(@Path("id") id : Int) : String

    @GET("users/reading")
    suspend fun getCurrentlyReadingBooks() : List<Book>

    @PUT("books/reading/{id}")
    suspend fun updateBookStatus(@Path("id") bookID: Int, @Body bookStatus : BookStatus) : JSONObject

    @DELETE("books/reading/{id}")
    suspend fun deleteCurrentlyReadingBook(@Path("id") bookID : Int) : JSONObject

    @GET("users/toberead")
    suspend fun getToBeReadBooks() : List<Book>

}