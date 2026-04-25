package com.bookhub.bookhub.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bookhub.bookhub.ui.screens.add_book.AddBookDetailedScreen
import com.bookhub.bookhub.ui.screens.add_book.AddBookScreen
import com.bookhub.bookhub.ui.screens.currently_reading.CurrentlyReadingScreen
import com.bookhub.bookhub.ui.screens.currently_reading_detail.CurrentlyReadingDetailScreen
import com.bookhub.bookhub.ui.screens.home.HomeScreen
import com.bookhub.bookhub.ui.screens.login.LoginScreen
import com.bookhub.bookhub.ui.screens.main.MainScreen
import com.bookhub.bookhub.ui.screens.newsfeed.NewsFeedScreen
import com.bookhub.bookhub.ui.screens.register.RegisterScreen
import com.bookhub.bookhub.ui.screens.register.SelectGenresScreen
import com.bookhub.bookhub.ui.screens.register.SetPasswordScreen
import com.bookhub.bookhub.ui.screens.search.SearchScreen
import com.bookhub.bookhub.ui.screens.userprofile.UserProfileScreen

sealed class BottomNavigationScreen(val route: String,val icon : ImageVector) {
    data object Home : BottomNavigationScreen("home",Icons.Filled.Home)
    data object CurrentlyReading : BottomNavigationScreen("currently-reading", Icons.Filled.Book)
    data object NewsFeed : BottomNavigationScreen("news-feed", Icons.Filled.Group)
    data object UserProfile : BottomNavigationScreen("user-profile", Icons.Filled.Person)
}

sealed class BookHubNavigation(val route: String){
    data object Login : BookHubNavigation("login")
    data object MainScreen : BookHubNavigation("mainScreen")
    data object Register : BookHubNavigation("register")
    data object SetPassword : BookHubNavigation("setPassword")
    data object SelectGenres : BookHubNavigation("selectGenres")
    data object AddBook : BookHubNavigation("addBook")
    data object CurrentlyReadingDetail : BookHubNavigation("currentlyReadingDetail/")
    data object SearchScreen : BookHubNavigation("searchScreen")
    data object AddBookDetail : BookHubNavigation("addBookDetail/")
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(isLoggedIn : Boolean){
    val navController = rememberNavController()
    val initialDest = if(isLoggedIn) BookHubNavigation.MainScreen.route else BookHubNavigation.Login.route
    NavHost(navController = navController, startDestination = initialDest){
        composable(BookHubNavigation.Login.route){ LoginScreen(navController) }
        composable(BookHubNavigation.MainScreen.route){ MainScreen(navController) }
        composable(BookHubNavigation.Register.route){ RegisterScreen(navController) }
        composable(BookHubNavigation.SetPassword.route){ SetPasswordScreen(navController)}
        composable(BookHubNavigation.SelectGenres.route){ SelectGenresScreen(navController) }
        composable("${BookHubNavigation.CurrentlyReadingDetail.route}{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType })) {
            CurrentlyReadingDetailScreen(navController)
        }
        composable(BookHubNavigation.SearchScreen.route){ SearchScreen() }
        composable(BookHubNavigation.AddBook.route,
            exitTransition = {slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))},
            popEnterTransition = {slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))},
        ){
            AddBookScreen(navController)
        }
        composable("${BookHubNavigation.AddBookDetail.route}{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType}),
            enterTransition = {slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))},
            exitTransition = {slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))}
        ){
            AddBookDetailedScreen(navController)
        }
    }
}

@Composable
fun BottomBarNavigation(navController : NavHostController,outerNavController: NavHostController, padding : PaddingValues){
    NavHost(navController, startDestination = BottomNavigationScreen.Home.route,
        Modifier.padding(padding)){
        composable(BottomNavigationScreen.Home.route){ HomeScreen(outerNavController) }
        composable(BottomNavigationScreen.CurrentlyReading.route){ CurrentlyReadingScreen(outerNavController) }
        composable(BottomNavigationScreen.NewsFeed.route){ NewsFeedScreen(outerNavController) }
        composable(BottomNavigationScreen.UserProfile.route){ UserProfileScreen() }
    }
}