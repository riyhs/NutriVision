package com.nutrivision.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nutrivision.app.ui.screen.LoginScreen
import com.nutrivision.app.ui.screen.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Screen.Auth.route,
        startDestination = Screen.Auth.Login.route,
    ) {
        composable(
            route = Screen.Auth.Login.route
        ) {
            LoginScreen(
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
        ) {
            RegisterScreen(onNavigateBack = {
                navController.navigateUp()
            },
                onRegisterClicked = { name, email, password, confirmPassword ->
                    // TODO: Implementasikan logika registrasi Anda di sini.
                    // Ini biasanya memanggil fungsi pada ViewModel.
                    println("Registrasi diproses untuk: $email")

                    // Setelah registrasi, kembali ke halaman login.
                    navController.navigateUp()
                })
        }
    }
}