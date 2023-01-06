package com.bookhub.bookhub.ui.screens.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bookhub.bookhub.ui.BottomBarNavigation
import com.bookhub.bookhub.ui.BottomNavigationScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigationItems = listOf(BottomNavigationScreen.Home, BottomNavigationScreen.CurrentlyReading, BottomNavigationScreen.NewsFeed, BottomNavigationScreen.UserProfile)
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                //TODO elevation doesn't work, find out why
                elevation = 50.dp,
                modifier = Modifier.drawBehind {
                    val strokeWidth = 2f
                    val x = size.width - strokeWidth
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, 0f), //(0,0) at top-left point of the box
                        end = Offset(x, 0f), //top-right point of the box
                        strokeWidth = strokeWidth
                    )
                }
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, modifier = Modifier.scale(1.5f), contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ){
        BottomBarNavigation(navController, it)
    }
}