package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.models.Book

@Composable
fun BookItem(book : Book) {
    Row {
        Box(modifier = Modifier.height(100.dp).width(50.dp).background(color = Color.Gray))
        Text(book.name)
    }
}