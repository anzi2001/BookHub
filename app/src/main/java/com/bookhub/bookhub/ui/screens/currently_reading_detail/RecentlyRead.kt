package com.bookhub.bookhub.ui.screens.currently_reading_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.common.HeightSpacer

@Composable
fun RecentlyRead(recentlyReadList : List<String> = listOf()) {
    if(recentlyReadList.isEmpty()){
        Column{
            HeightSpacer(height = 20.dp)
            Text(stringResource(R.string.haventRead))
            HeightSpacer(height = 20.dp)
        }
    }
    else{
        Column {
            Text(stringResource(R.string.recentlyRead))
            LazyColumn{
                items(listOf<String>()){ item ->
                    RecentlyReadItem()
                }
            }
        }
    }

}