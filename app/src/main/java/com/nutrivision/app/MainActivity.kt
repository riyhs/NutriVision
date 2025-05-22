package com.nutrivision.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.nutrivision.app.ui.navigation.BottomBar
import com.nutrivision.app.ui.navigation.RootNavGraph
import com.nutrivision.app.ui.navigation.Screen
import com.nutrivision.app.ui.theme.NutriVisionTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriVisionTheme {
                navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val topBarState = rememberSaveable { (mutableStateOf(true)) }

                when (currentRoute) {
                    Screen.Main.Home.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    Screen.Main.Scan.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    Screen.Main.Profile.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    else -> {
                        bottomBarState.value = false
                        topBarState.value = false
                    }
                }

                Scaffold(
                    bottomBar = {
                        if (bottomBarState.value) {
                            BottomBar(
                                navController = navController,
                            )
                        }
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        RootNavGraph(
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}

