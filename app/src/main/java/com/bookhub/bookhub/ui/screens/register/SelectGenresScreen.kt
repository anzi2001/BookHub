package com.bookhub.bookhub.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.theme.SubtitleStyle
import com.bookhub.bookhub.ui.theme.TitleStyle

@Composable
fun SelectGenresScreen(navController: NavController) {
    val genres = List(9) { "Romance" }

    Column{
        TopDecor()
        Column(modifier = Modifier.padding(20.dp)){
            Text(stringResource(R.string.selectGenresTitle), style = TitleStyle)
            HeightSpacer(height = 20.dp)
            Text(stringResource(R.string.selectGenresSubtitle), style = SubtitleStyle)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(genres) { genre ->
                    GenreItem(genre = genre)
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight())
            BHButton(stringResource(R.string.continueButton)) {
                navController.navigate(BookHubNavigation.MainScreen.route){
                    popUpTo(BookHubNavigation.MainScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }


}