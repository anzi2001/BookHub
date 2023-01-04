package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun SearchBar(placeholder : String = "", onTextChanged : (String) -> Unit) {
    TextField(
        shape = RoundedCornerShape(20),
        placeholder = { Text(placeholder) },
        value = "",
        onValueChange = onTextChanged,
        trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search icon") }
    )
}