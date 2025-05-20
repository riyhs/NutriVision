package com.nutrivision.app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column {
            Text(
                text = "Login",
                modifier = Modifier.padding(innerPadding)
            )

            Button(
                onClick = { onNavigateToHome() },
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "Login")
            }
        }
    }
}