package com.bookhub.bookhub.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.theme.AuthorStyle
import com.bookhub.bookhub.ui.theme.BackgroundGray
import com.bookhub.bookhub.ui.theme.LightGray


@Composable
fun SearchBookItem(book : Book, modifier : Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = LightGray
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ){
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Crop,
                model = book.image,
                contentDescription = book.title,
                placeholder = ColorPainter(BackgroundGray)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(3f)) {
                Text(book.author, style = AuthorStyle)
                HeightSpacer(height = 10.dp)
                Text(book.title, fontWeight = FontWeight.Bold)
            }
        }
    }

}