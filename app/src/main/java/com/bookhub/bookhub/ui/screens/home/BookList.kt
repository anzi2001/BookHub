package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bookhub.bookhub.models.Book

@Composable
fun BookList(title : String, books : List<Book>) {
    Column {
        Text(title)
        LazyRow{
            items(books){ book ->
                BookItem(book)
            }
        }
    }
}