package com.bookhub.bookhub.ui.screens.currently_reading

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.MAX_BOOK_STARS
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.theme.AuthorStyle
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.BookTitleStyle
import com.bookhub.bookhub.ui.theme.Gray

@Composable
fun CurrentlyReadingBook(book : Book, modifier : Modifier = Modifier, buttonText : String, onButtonClick : () -> Unit) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp).weight(1f)) {
            Text(book.author, style = AuthorStyle)
            HeightSpacer(height = 10.dp)
            Text(book.title, style = BookTitleStyle)
            HeightSpacer(height = 20.dp)
            Row{
                for(i: Int in 0 until book.averageRating.toInt()){
                    Icon(Icons.Filled.Star, contentDescription = "Full star", tint = Color.Yellow)
                }
                for(i: Int in 0 until (MAX_BOOK_STARS - book.averageRating.toInt())){
                    Icon(Icons.Filled.Star, contentDescription = "Empty star", tint = Color.LightGray)
                }
            }
            Text(stringResource(R.string.totalReviews, "712k"))
            HeightSpacer(height = 20.dp)
            BHButton(text = buttonText, modifier = Modifier, true ,onButtonClick)
        }
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .size(100.dp, 220.dp),
            contentScale = ContentScale.Crop,
            model = book.image,
            contentDescription = book.title,
            placeholder = ColorPainter(Gray)
        )
    }
}

@Composable
@Preview
fun CurrentlyReadingBookPreview(){
    BookHubTheme {
        //CurrentlyReadingBook(book = Book(1,"",null,))
    }
}