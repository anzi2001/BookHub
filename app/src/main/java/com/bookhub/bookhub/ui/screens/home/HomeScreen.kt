package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun HomeScreen(outerNavController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchBar(
            placeholder = stringResource(R.string.searchFor),
            modifier = Modifier.clickable {
                outerNavController.navigate(BookHubNavigation.SearchScreen.route)
            }.padding(16.dp).fillMaxWidth()
        ){}
        Text(stringResource(id = R.string.welcomeBack, "Anže"), style = TitleStyle)
        HeightSpacer(height = 10.dp)
        BHButton(text = stringResource(R.string.addBook)) {
            outerNavController.navigate(BookHubNavigation.AddBook.route)
        }
        HeightSpacer(height = 20.dp)
        BookList(title = stringResource(R.string.toBeRead), books = listOf(), modifier = Modifier.fillMaxWidth())
        HeightSpacer(height = 20.dp)
        BookList(title = stringResource(R.string.collections), books = listOf(), modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    BookHubTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(navController)
        }
    }
}