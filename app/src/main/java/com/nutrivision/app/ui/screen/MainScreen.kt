package com.nutrivision.app.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nutrivision.app.ui.navigation.bottomappbar.BottomBar
import com.nutrivision.app.ui.navigation.RootNavGraph
import com.nutrivision.app.ui.navigation.Screen
import com.nutrivision.app.ui.navigation.topappbar.AppTopBar
import com.nutrivision.app.ui.navigation.topappbar.ChildAppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(isAuthenticated: Boolean, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

    val currentScreenTypeState = rememberSaveable { (mutableStateOf("")) }

    val topAppBarState = rememberTopAppBarState()
    val topAppBarTitle = rememberSaveable { (mutableStateOf("")) }
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)

    when (currentRoute) {
        Screen.Main.Home.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppBarTitle.value = "Home"
            currentScreenTypeState.value = "main"
        }

        Screen.Main.Scan.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppBarTitle.value = "Scan"
            currentScreenTypeState.value = "main"
        }

        Screen.Main.Profile.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppBarTitle.value = "Profile"
            currentScreenTypeState.value = "main"
        }

        Screen.Main.History.route -> {
            showBottomBarState.value = false
            showTopBarState.value = true
            topAppBarTitle.value = "Scan History"
            currentScreenTypeState.value = "child"
        }

        Screen.Main.BMI.route -> {
            showBottomBarState.value = false
            showTopBarState.value = true
            topAppBarTitle.value = "BMI Calculator"
            currentScreenTypeState.value = "child"
        }

        else -> {
            showBottomBarState.value = false
            showTopBarState.value = false
            currentScreenTypeState.value = "child"
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBarState.value) {
                BottomBar(
                    navController = navController,
                )
            }
        },
        topBar = {
            if (showTopBarState.value && currentScreenTypeState.value == "main") {
                AppTopBar(
                    toolbarTitle = topAppBarTitle.value,
                    barScrollBehavior = barScrollBehavior
                )
            } else if (showTopBarState.value && currentScreenTypeState.value == "child") {
                ChildAppTopBar(
                    toolbarTitle = topAppBarTitle.value,
                    barScrollBehavior = barScrollBehavior,
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            RootNavGraph(
                navHostController = navController,
                startDestination = if (isAuthenticated) Screen.Main.route else Screen.Auth.route,
            )
        }
    }
}