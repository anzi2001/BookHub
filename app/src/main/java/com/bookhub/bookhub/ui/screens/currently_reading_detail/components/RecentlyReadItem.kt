package com.bookhub.bookhub.ui.screens.currently_reading_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bookhub.bookhub.ui.theme.Gray

@Composable
fun RecentlyReadItem() {
    Card(
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(containerColor = Gray)
    ) {
        Row{
            Text("20.12.2022")
            Spacer(modifier = Modifier.fillMaxWidth())
            Text("1:08")
        }
    }
}