package com.nutrivision.app.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nutrivision.app.ui.navigation.bottomappbar.BottomBar
import com.nutrivision.app.ui.navigation.RootNavGraph
import com.nutrivision.app.ui.navigation.Screen
import com.nutrivision.app.ui.navigation.topappbar.AppTopBar
import com.nutrivision.app.ui.navigation.topappbar.ChildAppTopBar
import com.nutrivision.app.ui.viewmodel.AuthState
import com.nutrivision.app.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()
    val startDestination = when (authState) {
        is AuthState.Authenticated -> Screen.Main.route
        else -> Screen.Auth.route
    }

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

        Screen.Main.Detail.route -> {
            showBottomBarState.value = false
            showTopBarState.value = true
            topAppBarTitle.value = "Detail Product"
            currentScreenTypeState.value = "child"
        }

        Screen.Main.EditProfile.route -> {
            showBottomBarState.value = false
            showTopBarState.value = true
            topAppBarTitle.value = "Ubah Profil"
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
                startDestination = startDestination,
                authViewModel = authViewModel
            )
        }
    }
}