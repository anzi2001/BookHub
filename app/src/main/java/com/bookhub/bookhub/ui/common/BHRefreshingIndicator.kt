package com.bookhub.bookhub.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BHRefreshingIndicator(){
    Box(modifier = Modifier.fillMaxWidth()){
        PullRefreshIndicator(
            true,
            rememberPullRefreshState(
                refreshing = true,
                onRefresh = {}
            ),
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}