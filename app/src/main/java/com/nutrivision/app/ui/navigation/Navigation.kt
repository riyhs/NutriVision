package com.nutrivision.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nutrivision.app.R
import com.nutrivision.app.ui.screen.HomeScreen
import com.nutrivision.app.ui.screen.LoginScreen


private object Route {
    // AUTH GRAPH
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"

    // MAIN GRAPH
    const val MAIN = "main"
    const val HOME = "home"
    const val SCAN = "scan"
    const val PROFILE = "profile"
}

sealed class TopLevelDestination(
    val route: String,
    val title: Int? = null,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val navArguments: List<NamedNavArgument> = emptyList()
)

sealed class Screen(val route: String) {
    object Auth: Screen(Route.AUTH) {
        object Login : Screen(Route.LOGIN)
        object Register : Screen(Route.REGISTER)
    }

    object Main: TopLevelDestination(Route.MAIN) {
        object Home : TopLevelDestination(
            route = Route.HOME,
            title = R.string.home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
        object Scan : TopLevelDestination(
            route = Route.SCAN,
            title = R.string.scan,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
        object Profile : TopLevelDestination(
            route = Route.PROFILE,
            title = R.string.profile,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
    }
}

@Composable
fun RootNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Auth.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    RootNavHost(
        navController = navHostController,
        startDestination = Screen.Auth.route,
    )
}
