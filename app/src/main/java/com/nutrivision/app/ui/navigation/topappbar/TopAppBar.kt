package com.nutrivision.app.ui.navigation.topappbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    toolbarTitle: String,
    barScrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        scrollBehavior = barScrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(toolbarTitle, style = MaterialTheme.typography.titleMedium)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildAppTopBar(
    toolbarTitle: String,
    barScrollBehavior: TopAppBarScrollBehavior,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        scrollBehavior = barScrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(toolbarTitle, style = MaterialTheme.typography.titleMedium)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Up Button"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}