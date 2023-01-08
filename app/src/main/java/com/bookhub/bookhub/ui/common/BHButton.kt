package com.bookhub.bookhub.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bookhub.bookhub.ui.theme.BHBUttonColor
import com.bookhub.bookhub.ui.theme.BookHubTheme

@Composable
fun BHButton(text : String, modifier : Modifier = Modifier, enabled : Boolean = true,  onClick : () -> Unit) {
    Button(
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = BHBUttonColor)
    ) {
        Text(text, fontSize = 20.sp, modifier = Modifier.padding(10.dp))
    }
}

@Composable
@Preview
fun BHbuttonPreview(){
    BookHubTheme {
        BHButton(text = "Register"){}
    }
}