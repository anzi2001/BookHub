package com.bookhub.bookhub.ui.theme

import android.app.Activity
import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = OrangeBrown,
    secondary = Brown,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = OrangeBrown,
    secondary = Brown,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

const val TransitionAnimationDuration = 150

val TitleStyle = TextStyle(fontSize = 40.sp, color = TitleColor)
val SubtitleStyle = TextStyle(fontSize = 20.sp, color = Color.Gray)

val LoginTitleStyle = TextStyle(fontSize = 30.sp, color = TitleColor, fontWeight = FontWeight.Bold)
val AuthSubtitleStyle = TextStyle(fontSize = 15.sp, color = Color.Gray)


val AuthorStyle = TextStyle(fontSize = 15.sp, color = AuthorGray)
val BookTitleStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold,color = Color.Black)
val UserNameStyle = TextStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
val FollowingStyle = TextStyle(fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.SemiBold)

@Composable
fun BookHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}