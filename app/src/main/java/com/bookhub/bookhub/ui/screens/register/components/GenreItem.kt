package com.bookhub.bookhub.ui.screens.register.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bookhub.bookhub.ui.theme.BHTextFieldColor
import com.bookhub.bookhub.ui.theme.OrangeBrown
import com.bookhub.bookhub.ui.theme.TransitionAnimationDuration

@Composable
fun GenreItem(genre : String, onItemClick: (String, Boolean) -> Unit) {
    var isSelected by remember{ mutableStateOf(false) }
    val cardColor by animateColorAsState(
        targetValue = if(isSelected) OrangeBrown else BHTextFieldColor,
        animationSpec = tween(TransitionAnimationDuration,0, EaseInOut)
    )
    val textColor by animateColorAsState(
        targetValue = if(isSelected) Color.White else Color.Black,
        animationSpec = tween(TransitionAnimationDuration,0, EaseInOut)
    )
    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier.clip(RoundedCornerShape(50)).clickable {
            isSelected = !isSelected
            onItemClick(genre, isSelected)
        },
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = textColor
        )
    ){
        Text(
            text = genre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}