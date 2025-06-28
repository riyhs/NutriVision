package com.nutrivision.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nutrivision.app.ui.screen.LoginScreen
import com.nutrivision.app.ui.screen.RegisterScreen
import com.nutrivision.app.ui.viewmodel.AuthViewModel

fun NavGraphBuilder.authNavGraph(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    navigation(
        route = Screen.Auth.route,
        startDestination = Screen.Auth.Login.route,
    ) {
        composable(
            route = Screen.Auth.Login.route
        ) { backStackEntry ->
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToHome = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Auth.Register.route)
                }
            )
        }

        composable(
            route = Screen.Auth.Register.route
        ) { backStackEntry ->
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
    }
}