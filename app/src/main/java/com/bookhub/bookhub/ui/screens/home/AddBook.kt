package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.theme.BackgroundGray

@Composable
fun AddBook(modifier : Modifier = Modifier) {
    Column{
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(100.dp, 150.dp)
                .background(color = BackgroundGray)
        ){
            Icon(Icons.Outlined.AddCircle, modifier = Modifier.scale(1.3f), contentDescription = stringResource(R.string.addBook))
        }
        Text(stringResource(R.string.addBook), textAlign = TextAlign.Center, style = BookStyle, modifier = Modifier.width(100.dp))
    }

}