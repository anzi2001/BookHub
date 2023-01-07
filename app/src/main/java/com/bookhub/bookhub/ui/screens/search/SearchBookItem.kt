package com.bookhub.bookhub.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.models.Book

@Composable
fun SearchBookItem(book : Book) {
    Row {
        Box(Modifier
                .height(50.dp)
                .width(30.dp)
        )
        Column {
            Text(book.author)
            Text(book.title)
        }
    }
}