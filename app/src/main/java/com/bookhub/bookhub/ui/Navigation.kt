package com.bookhub.bookhub.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
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
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable as animatedComposable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class BottomNavigationScreen(val route: String,val icon : ImageVector) {
    object Home : BottomNavigationScreen("home",Icons.Filled.Home)
    object CurrentlyReading : BottomNavigationScreen("currently-reading", Icons.Filled.Book)
    object NewsFeed : BottomNavigationScreen("news-feed", Icons.Filled.Group)
    object UserProfile : BottomNavigationScreen("user-profile", Icons.Filled.Person)
}

sealed class BookHubNavigation(val route: String){
    object Login : BookHubNavigation("login")
    object MainScreen : BookHubNavigation("mainScreen")
    object Register : BookHubNavigation("register")
    object SetPassword : BookHubNavigation("setPassword")
    object SelectGenres : BookHubNavigation("selectGenres")
    object AddBook : BookHubNavigation("addBook")
    object CurrentlyReadingDetail : BookHubNavigation("currentlyReadingDetail/")
    object SearchScreen : BookHubNavigation("searchScreen")
    object AddBookDetail : BookHubNavigation("addBookDetail/")
}

val tweenSpec = tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f,0.93f,0.68f,1.27f))

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(isLoggedIn : Boolean){
    val navController = rememberAnimatedNavController()
    val initialDest = if(isLoggedIn) BookHubNavigation.MainScreen.route else BookHubNavigation.Login.route
    AnimatedNavHost(navController = navController, startDestination = initialDest){
        animatedComposable(BookHubNavigation.Login.route){ LoginScreen(navController) }
        animatedComposable(BookHubNavigation.MainScreen.route){ MainScreen(navController) }
        animatedComposable(BookHubNavigation.Register.route){ RegisterScreen(navController) }
        animatedComposable(BookHubNavigation.SetPassword.route){ SetPasswordScreen(navController)}
        animatedComposable(BookHubNavigation.SelectGenres.route){ SelectGenresScreen(navController) }
        animatedComposable("${BookHubNavigation.CurrentlyReadingDetail.route}{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType })) {
            CurrentlyReadingDetailScreen(navController)
        }
        animatedComposable(BookHubNavigation.SearchScreen.route){ SearchScreen() }
        animatedComposable(BookHubNavigation.AddBook.route,
            exitTransition = {slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))},
            popEnterTransition = {slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))},
        ){
            AddBookScreen(navController)
        }
        animatedComposable("${BookHubNavigation.AddBookDetail.route}{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType}),
            enterTransition = {slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))},
            exitTransition = {slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))}
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