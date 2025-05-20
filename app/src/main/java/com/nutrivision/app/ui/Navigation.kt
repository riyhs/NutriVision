package com.nutrivision.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nutrivision.app.ui.screen.HomeScreen
import com.nutrivision.app.ui.screen.LoginScreen

private object Route {
    const val LOGIN = "login"
    const val HOME = "home"
}

sealed class Screen(val route: String) {
    object Login: Screen(Route.LOGIN)
    object Home: Screen(Route.HOME)
}

@Composable
fun RootNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}