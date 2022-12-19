package com.bookhub.bookhub.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bookhub.bookhub.ui.theme.BHTextFieldColor
import com.bookhub.bookhub.ui.theme.BookHubTheme

@Composable
fun BHInputField(value : String = "", placeholder: String = "", onTextChanged : (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20),
        value = value,
        onValueChange = onTextChanged,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BHTextFieldColor,
            textColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun BHInputFieldPreview(){
    BookHubTheme {
        BHInputField(""){}
    }
}