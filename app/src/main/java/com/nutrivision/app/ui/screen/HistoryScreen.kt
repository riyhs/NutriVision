package com.nutrivision.app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.ui.viewmodel.ScanViewModel

@Composable
fun HistoryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: ScanViewModel,
    modifier: Modifier = Modifier
) {
    val historyItems by viewModel.allHistoryItems.collectAsState()
    var showClearConfirmDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (historyItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Belum ada riwayat pemindaian.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(historyItems) { historyItem ->
                        HistoryItemCard(
                            historyItem = historyItem,
                            onClick = {
                                onNavigateToDetail(historyItem.productCode)
                            }
                        )
                    }
                }
            }
        }

        if (showClearConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showClearConfirmDialog = false },
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                title = {
                    Text(
                        "Konfirmasi Hapus",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                text = {
                    Text(
                        "Apakah Anda yakin ingin menghapus semua riwayat pemindaian? Tindakan ini tidak dapat dibatalkan.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.clearAllHistory()
                            showClearConfirmDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Hapus Semua", color = MaterialTheme.colorScheme.onError)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearConfirmDialog = false }) {
                        Text("Batal", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    }
}

@Composable
fun HistoryItemCard(
    historyItem: ScanHistoryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(historyItem.imageUrl)
                    .crossfade(true)
                    .error(android.R.drawable.ic_menu_gallery)
                    .placeholder(android.R.drawable.stat_notify_sync)
                    .build(),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    }
                },
                error = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No Image", style = MaterialTheme.typography.labelSmall)
                    }
                },
                contentDescription = "Image of ${historyItem.productName}",
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = historyItem.productName,
                    style = MaterialTheme.typography.headlineSmall
                )
                if (!historyItem.productBrand.isNullOrBlank()) {
                    Text(
                        text = historyItem.productBrand,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}