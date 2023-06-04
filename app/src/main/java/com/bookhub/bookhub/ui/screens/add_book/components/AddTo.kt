package com.bookhub.bookhub.ui.screens.add_book.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bookhub.bookhub.R
import com.bookhub.bookhub.models.BookStatusEnums
import com.bookhub.bookhub.ui.common.BHButton
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.theme.BHBUttonColor
import com.bookhub.bookhub.ui.theme.Gray
import com.bookhub.bookhub.ui.screens.add_book.AddBookDetailViewModel

@Composable
fun AddTo(addBookDetailViewModel: AddBookDetailViewModel) {
    val uiState by addBookDetailViewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(stringResource(R.string.addTo))
        BHButton(text = stringResource(R.string.reading),
            buttonColor = if(uiState.bookStatus?.status == BookStatusEnums.READING) BHBUttonColor else Gray) {
            addBookDetailViewModel.updateBookState(BookStatusEnums.READING)
        }
        HeightSpacer(height = 20.dp)
        BHButton(text = stringResource(R.string.toBeRead),
            buttonColor = if(uiState.bookStatus?.status == BookStatusEnums.TOBEREAD) BHBUttonColor else Gray) {
            addBookDetailViewModel.updateBookState(BookStatusEnums.TOBEREAD)
        }
        HeightSpacer(height = 20.dp)
        BHButton(text = stringResource(R.string.read),
            buttonColor = if(uiState.bookStatus?.status == BookStatusEnums.READ) BHBUttonColor else Gray) {
            addBookDetailViewModel.updateBookState(BookStatusEnums.READ)
        }
    }
}