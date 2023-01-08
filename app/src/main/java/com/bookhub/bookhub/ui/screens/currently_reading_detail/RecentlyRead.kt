package com.bookhub.bookhub.ui.screens.currently_reading_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bookhub.bookhub.R

@Composable
fun RecentlyRead() {
    Column {
        Text(stringResource(R.string.recentlyRead))
        LazyColumn{
            items(listOf<String>()){ item ->
                RecentlyReadItem()
            }
        }
    }
}