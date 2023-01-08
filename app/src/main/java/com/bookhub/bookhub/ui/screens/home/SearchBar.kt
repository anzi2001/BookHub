package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.Gray

@Composable
fun SearchBar(modifier: Modifier = Modifier, value : String = "", placeholder : String = "", enabled : Boolean = true, onTextChanged : (String) -> Unit) {
    TextField(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(35),
        placeholder = { Text(placeholder) },
        value = value,
        singleLine = true,
        onValueChange = onTextChanged,
        trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search icon") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
@Preview
fun SearchBarPreview(){
    BookHubTheme {
        SearchBar{

        }
    }

}