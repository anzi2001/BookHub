package com.bookhub.bookhub.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.ui.screens.book.BookScreen
import com.bookhub.bookhub.ui.screens.home.HomeScreen
import com.bookhub.bookhub.ui.screens.login.LoginScreen
import com.bookhub.bookhub.ui.screens.main.MainScreen
import com.bookhub.bookhub.ui.screens.newsfeed.NewsFeedScreen
import com.bookhub.bookhub.ui.screens.register.RegisterScreen
import com.bookhub.bookhub.ui.screens.userprofile.UserProfileScreen

sealed class BottomNavigationScreen(val route: String, ) {
    object Home : BottomNavigationScreen("home")
    object Books : BottomNavigationScreen("books")
    object NewsFeed : BottomNavigationScreen("news-feed")
    object UserProfile : BottomNavigationScreen("user-profile")
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){ LoginScreen(navController) }
        composable("mainScreen"){ MainScreen() }
        composable("register"){ RegisterScreen() }
    }
}

@Composable
fun BottomBarNavigation(padding : PaddingValues){
    val navController = rememberNavController()
    NavHost(navController, startDestination = BottomNavigationScreen.Home.route, androidx.compose.ui.Modifier.padding(padding)){
        composable(BottomNavigationScreen.Home.route){ HomeScreen() }
        composable(BottomNavigationScreen.Books.route){ BookScreen() }
        composable(BottomNavigationScreen.NewsFeed.route){ NewsFeedScreen() }
        composable(BottomNavigationScreen.UserProfile.route){ UserProfileScreen() }
    }
}