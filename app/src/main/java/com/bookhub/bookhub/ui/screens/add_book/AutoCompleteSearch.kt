package com.bookhub.bookhub.ui.screens.add_book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.screens.home.SearchBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AutoCompleteSearch(searchValue : String, searchResults : List<Book>, onValueChange : (String) -> Unit, modifier : Modifier = Modifier) {
    var exp by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(modifier = modifier, expanded = exp, onExpandedChange = { exp = !exp }) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onTextChanged = onValueChange,
        )
        if (searchResults.isNotEmpty()) {
            MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20))){
                ExposedDropdownMenu(
                    expanded = exp,
                    onDismissRequest = { exp = false },
                    modifier = Modifier.background(Color(0xFFEEEEEE)).height(100.dp)) {
                    searchResults.forEach { option ->
                        DropdownMenuItem(
                            //modifier = Modifier.background(),
                            onClick = {
                                onValueChange(option.title)
                                exp = false
                            }
                        ) {
                            Text(text = option.title)
                        }
                    }
                }
            }

        }
    }
}