package com.nutrivision.app.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nutrivision.app.ui.screen.BMIScreen
import com.nutrivision.app.ui.screen.DetailScreen
import com.nutrivision.app.ui.screen.HistoryScreen
import com.nutrivision.app.ui.screen.HomeScreen
import com.nutrivision.app.ui.screen.ProfileScreen
import com.nutrivision.app.ui.screen.ScanScreen
import com.nutrivision.app.ui.viewmodel.AuthViewModel
import com.nutrivision.app.ui.viewmodel.ScanViewModel

fun NavGraphBuilder.mainNavGraph(
    authViewModel: AuthViewModel,
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
                },
                onNavigateToScan = {
                    navController.navigate(Screen.Main.Scan.route)
                }
            )
        }

        composable(
            route = Screen.Main.Scan.route
        ) { backStackEntry ->
            val mainNavGraphEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Main.route)
            }
            val scanViewModel: ScanViewModel = hiltViewModel(mainNavGraphEntry)

            ScanScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToDetail = { productCode ->
                    navController.navigate("detail/$productCode")
                },
                viewModel = scanViewModel
            )
        }

        composable(
            route = Screen.Main.Profile.route
        ) {
            ProfileScreen(
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Main.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Main.History.route
        ) { backStackEntry ->
            val mainNavGraphEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Main.route)
            }
            val scanViewModel: ScanViewModel = hiltViewModel(mainNavGraphEntry)

            HistoryScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToDetail = { productCode ->
                    navController.navigate("detail/$productCode")
                },
                viewModel = scanViewModel
            )
        }

        composable(
            route = Screen.Main.BMI.route
        ) {
            BMIScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = Screen.Main.Detail.route
        ) { backStackEntry ->
            val mainNavGraphEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Main.route)
            }
            val scanViewModel: ScanViewModel = hiltViewModel(mainNavGraphEntry)
            val productCode = backStackEntry.arguments?.getString("productCode")

            DetailScreen(
                productCode = productCode,
                onNavigateBack = {
                    navController.navigateUp()
                },
                viewModel = scanViewModel
            )
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