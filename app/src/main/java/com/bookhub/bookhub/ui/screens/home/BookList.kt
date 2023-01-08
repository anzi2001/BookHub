package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.BookHubNavigation

@Composable
fun BookList(
    modifier : Modifier = Modifier,
    title : String,
    books : List<Book>,
    outerNavController : NavHostController,
    onBookClick: (Book) -> Unit,
) {
    Column(modifier = modifier) {
        Text(title)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            items(books){ book ->
                BookItem(book, onBookClick)
            }
            item{
                AddBook(modifier.clickable {
                    outerNavController.navigate(BookHubNavigation.AddBook.route)
                })
            }
        }
    }
}