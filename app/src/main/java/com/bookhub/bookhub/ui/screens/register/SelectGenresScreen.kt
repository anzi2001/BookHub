package com.bookhub.bookhub.ui.screens.register

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.BookHubNavigation
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.BHRefreshingIndicator
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.TopDecor
import com.bookhub.bookhub.ui.screens.register.components.GenreItem
import com.bookhub.bookhub.ui.theme.AuthSubtitleStyle
import com.bookhub.bookhub.ui.theme.LoginTitleStyle

@Composable
fun SelectGenresScreen(navController: NavController, registerViewModel: RegisterViewModel = hiltViewModel(LocalContext.current as ComponentActivity)) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()
    val genres = List(9) { "Romance" }

    val context = LocalContext.current

    if(uiState.error != null){
        LaunchedEffect(uiState.error){
            Toast.makeText(context, uiState.error, Toast.LENGTH_LONG).show()
        }
    }

    if(uiState.isRegistered){
        LaunchedEffect(Unit){
            navController.navigate(BookHubNavigation.Login.route){
                popUpTo(BookHubNavigation.Login.route){ inclusive = true }
            }
        }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        TopDecor(
            showBackButton = true,
            onBackClick = navController::popBackStack
        )
        Column(modifier = Modifier
            .padding(20.dp)
            .weight(1f)){
            Text(stringResource(R.string.selectGenresTitle), style = LoginTitleStyle)
            HeightSpacer(height = 20.dp)
            Text(stringResource(R.string.selectGenresSubtitle), style = AuthSubtitleStyle)
            HeightSpacer(height = 10.dp)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(genres) { genre ->
                    GenreItem(genre = genre){ selectedGenre, selected ->
                        registerViewModel.updateSelectedGenres(selectedGenre, selected)
                    }
                }
            }
        }
        BHButton(
            modifier = Modifier.padding(20.dp),
            text = stringResource(R.string.continueButton)
        ) {
            registerViewModel.register()
        }
    }
    if(uiState.isLoading){
        BHRefreshingIndicator()
    }
}