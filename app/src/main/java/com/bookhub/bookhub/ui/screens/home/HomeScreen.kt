package com.bookhub.bookhub.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHRefreshingIndicator
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.screens.home.components.BookList
import com.bookhub.bookhub.ui.screens.home.components.SearchBar
import com.bookhub.bookhub.ui.theme.BookHubTheme
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun HomeScreen(outerNavController: NavHostController, homeViewModel : HomeViewModel = hiltViewModel()) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(rememberScrollState())) {
        SearchBar(
            enabled = false,
            placeholder = stringResource(R.string.searchFor),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable {
                    outerNavController.navigate(BookHubNavigation.SearchScreen.route)
                }
        ){}
        Text(stringResource(id = R.string.welcomeBack, "Anže"), style = TitleStyle, modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpacer(height = 10.dp)
        BHButton(text = stringResource(R.string.addBook), modifier = Modifier.padding(horizontal = 16.dp)) {
            outerNavController.navigate(BookHubNavigation.AddBook.route)
        }
        HeightSpacer(height = 20.dp)
        BookList(
            title = stringResource(R.string.reading),
            books = uiState.currentlyReading ?: listOf(),
            modifier = Modifier.fillMaxWidth(),
            outerNavController = outerNavController) { book ->
            outerNavController.navigate("${BookHubNavigation.CurrentlyReadingDetail.route}${book.id}")
        }
        HeightSpacer(height = 20.dp)
        BookList(title = stringResource(R.string.toBeRead),
            books = uiState.toBeRead ?: listOf(),
            modifier = Modifier.fillMaxWidth(),
            outerNavController = outerNavController){ book ->
            outerNavController.navigate("${BookHubNavigation.CurrentlyReadingDetail.route}${book.id}")
        }
        HeightSpacer(height = 20.dp)
        BookList(title = stringResource(R.string.collections),
            books = listOf(),
            modifier = Modifier.fillMaxWidth(),
            outerNavController = outerNavController){

        }
    }
    if(uiState.isLoading){
        BHRefreshingIndicator()
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