package com.nutrivision.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nutrivision.app.R
import com.nutrivision.app.ui.viewmodel.AuthViewModel


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
    const val DETAIL = "detail/{productCode}"

    const val HISTORY = "history"
    const val BMI = "bmi"
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
            selectedIcon = Icons.Filled.CameraAlt,
            unselectedIcon = Icons.Outlined.CameraAlt
        )
        object Profile : TopLevelDestination(
            route = Route.PROFILE,
            title = R.string.profile,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.PersonOutline
        )

        object History : Screen(Route.HISTORY)
        object BMI : Screen(Route.BMI)
        object Detail : Screen(Route.DETAIL)
    }

}

@Composable
fun RootNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Auth.route,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(authViewModel, navController)
        mainNavGraph(authViewModel, navController)
    }
}

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    startDestination: String = Screen.Auth.route,
    authViewModel: AuthViewModel
) {
    RootNavHost(
        navController = navHostController,
        startDestination = startDestination,
        authViewModel = authViewModel,
        modifier = Modifier
    )
}
