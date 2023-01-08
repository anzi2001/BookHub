package com.bookhub.bookhub.ui.screens.currently_reading_detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.screens.currently_reading.CurrentlyReadingBook
import com.bookhub.bookhub.ui.theme.TitleStyle
import com.bookhub.bookhub.viewmodels.CurrentlyReadingDetailViewModel

@Composable
fun CurrentlyReadingDetailScreen(
    outerNavController : NavHostController,
    currentlyReadingViewModel : CurrentlyReadingDetailViewModel = hiltViewModel()
) {
    val book by currentlyReadingViewModel.book.observeAsState()
    val livecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    if(book == null){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) { CircularProgressIndicator() }
    }

    LaunchedEffect(Unit){
        currentlyReadingViewModel.error.observe(livecycleOwner){
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    Column{
        Text(stringResource(R.string.youAreCurrentlyReading), style = TitleStyle, modifier = Modifier.padding(horizontal = 16.dp))
        book?.let {
            CurrentlyReadingBook(book = it, buttonText = stringResource(R.string.change)) {
                outerNavController.popBackStack()
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)){
            RecentlyRead()
            HeightSpacer(height = 10.dp)
            Text(stringResource(R.string.totalHoursReading,"20h"), fontWeight = FontWeight.Bold)
            HeightSpacer(height = 20.dp)
            BHButton(text = stringResource(R.string.finishReading)) {
                //delete book from currentlyReading
            }
            HeightSpacer(height = 20.dp)
            BHButton(text = stringResource(R.string.startReading)) {
                //open timer screen
            }
        }


    }
}