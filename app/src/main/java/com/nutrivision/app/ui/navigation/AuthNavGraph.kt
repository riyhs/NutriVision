package com.nutrivision.app.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nutrivision.app.ui.screen.LoginScreen
import com.nutrivision.app.ui.screen.RegisterScreen
import com.nutrivision.app.ui.viewmodel.AuthViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Screen.Auth.route,
        startDestination = Screen.Auth.Login.route,
    ) {
        composable(
            route = Screen.Auth.Login.route
        ) { backStackEntry ->
            val authNavGraphEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Auth.route)
            }
            val authViewModel: AuthViewModel = hiltViewModel(authNavGraphEntry)

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
            val authNavGraphEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Auth.route)
            }
            val authViewModel: AuthViewModel = hiltViewModel(authNavGraphEntry)

            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onRegisterClicked = { name, email, password, confirmPassword ->
                    // TODO: Implementasikan logika registrasi Anda di sini.
                    println("Registrasi diproses untuk: $email")

                    // Setelah registrasi, kembali ke halaman login.
                    navController.navigateUp()
                })
        }
    }
}