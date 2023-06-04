package com.bookhub.bookhub.api

import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.BookRating
import com.bookhub.bookhub.models.BookStatus
import com.bookhub.bookhub.models.Response
import org.json.JSONObject

//TODO make a callAdapter for a Response<> wrapper
class BookRepo(private val bookApi : BookApi) {

    suspend fun getAllBooks() : Response<List<Book>> {
        return try{
            Response.Success(bookApi.allBooks())
        } catch(e :Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun getBook(bookID : Int) : Response<Book>{
        return try{
            Response.Success(bookApi.book(bookID))
        } catch(e :Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun getBookRatings(bookID: Int) : Response<List<BookRating>>{
        return try{
            Response.Success(bookApi.bookRatings(bookID))
        } catch(e: Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun searchBooks(searchQuery : String) : Response<List<Book>>{
        return try{
            Response.Success(bookApi.searchBooks(searchQuery))
        } catch(e : Exception){
            e.printStackTrace()
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun addBook(book : Book) : Response<Book>{
        return try{
            Response.Success(bookApi.addBook(book))
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun updateBook(bookID : Int, book: Book) : Response<Book>{
        return try{
            Response.Success(bookApi.updateBook(bookID, book))
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun deleteBook(bookID : Int) : Response<String>{
        return try{
            Response.Success(bookApi.deleteBook(bookID))
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun getCurrentlyReadingBooks() : Response<List<Book>>{
        return try{
            Response.Success(bookApi.getCurrentlyReadingBooks())
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun getToBeReadBooks() : Response<List<Book>>{
        return try{
            Response.Success(bookApi.getToBeReadBooks())
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }

    suspend fun updateBookStatus(bookID : Int, bookStatus : BookStatus) : Response<JSONObject>{
        return try{
            Response.Success(bookApi.updateBookStatus(bookID, bookStatus))
        } catch(e : Exception){
            Response.Error(e.localizedMessage)
        }
    }
}