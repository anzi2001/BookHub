package com.bookhub.bookhub.ui.screens.newsfeed

import android.media.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.Book
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.screens.home.SearchBar

@Composable
fun NewsFeedScreen(outerNavController: NavHostController
){
    SearchBar(
        enabled = false,
        placeholder = stringResource(R.string.searchFor),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){

    }
}