package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bookhub.bookhub.models.Book

@Composable
fun BookList(modifier : Modifier = Modifier, title : String, books : List<Book>) {
    Column(modifier = modifier) {
        Text(title)
        LazyRow{
            items(books){ book ->
                BookItem(book)
            }
        }
    }
}