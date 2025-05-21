package com.nutrivision.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nutrivision.app.ui.screen.HomeScreen

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
                onNavigateToScan = {
                    navController.navigate(Screen.Main.Scan.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Main.Profile.route)
                }
            )
        }

        composable(
            route = Screen.Main.Scan.route
        ) {
//            Scan Screen
//            ScanScreen(onNavigateBack = {
//                navController.navigateUp()
//            })
        }

        composable(
            route = Screen.Main.Profile.route
        ) {
//            Profile Screen
//            ProfileScreen(onNavigateBack = {
//                navController.navigateUp()
//            })
        }

        // route back to auth graph
        composable(
            route = Screen.Main.Profile.route
        ) {
//            ProfileScreen(
//                navigateToLogin = {
//                    navController.navigate(Screen.Auth.route) {
//                        popUpTo(Screen.Main.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
        }

    }
}