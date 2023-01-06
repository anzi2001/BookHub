package com.bookhub.bookhub.ui.screens.currently_reading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.models.MAX_BOOK_STARS
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.theme.BookHubTheme

@Composable
fun CurrentlyReadingBook(book : Book, modifier : Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(stringResource(R.string.author))
            Text("bookTitle", )
            Row{
                for(i: Int in 0 until book.stars){
                    Icon(Icons.Filled.Star, contentDescription = "Full star", tint = Color.Yellow)
                }
                for(i: Int in 0 until (MAX_BOOK_STARS - book.stars)){
                    Icon(Icons.Filled.Star, contentDescription = "Empty star")
                }
            }
            Text(stringResource(R.string.totalReviews, "712k"))

            HeightSpacer(height = 10.dp)
            BHButton(text = stringResource(R.string.read)) {
                
            }

        }
        Image(Icons.Filled.Book, contentDescription = "book")
    }
}

@Composable
@Preview
fun CurrentlyReadingBookPreview(){
    BookHubTheme {
        CurrentlyReadingBook(book = Book("","",4))
    }
}