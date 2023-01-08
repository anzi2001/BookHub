package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.theme.AuthorGray
import com.bookhub.bookhub.ui.theme.BackgroundGray

val BookStyle : TextStyle = TextStyle(fontSize = 16.sp, color = AuthorGray)

@Composable
fun BookItem(book : Book, onBookClick: (Book) -> Unit) {
    Column(modifier = Modifier.clickable {
        onBookClick(book)
    }) {
        AsyncImage(
            modifier = Modifier.size(100.dp, 150.dp),
            contentScale = ContentScale.Crop,
            model = book.image,
            contentDescription = book.title,
            placeholder = ColorPainter(BackgroundGray)
        )
        Text(book.title, style = BookStyle,textAlign = TextAlign.Center, modifier = Modifier.width(100.dp))
    }
}