package com.nutrivision.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nutrivision.app.ui.screen.BMIScreen
import com.nutrivision.app.ui.screen.HistoryScreen
import com.nutrivision.app.ui.screen.HomeScreen
import com.nutrivision.app.ui.screen.ProfileScreen
import com.nutrivision.app.ui.screen.ScanScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Screen.Main.route,
        startDestination = Screen.Main.Home.route
    ) {
        composable(
            route = Screen.Main.Home.route
        ) {
            HomeScreen(
                onNavigateToHistory = {
                    navController.navigate(Screen.Main.History.route)
                },
                onNavigateToBMI = {
                    navController.navigate(Screen.Main.BMI.route)
                }
            )
        }

        composable(
            route = Screen.Main.Scan.route
        ) {
            ScanScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = Screen.Main.Profile.route
        ) {
            ProfileScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = Screen.Main.History.route
        ) {
            HistoryScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = Screen.Main.BMI.route
        ) {
            BMIScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }

        // route back to auth graph
//        composable(
//            route = Screen.Main.Profile.route
//        ) {
//            ProfileScreen(
//                navigateToLogin = {
//                    navController.navigate(Screen.Auth.route) {
//                        popUpTo(Screen.Main.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }

    }
}