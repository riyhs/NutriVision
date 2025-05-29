package com.nutrivision.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.nutrivision.app.utils.Utils.getImageUrl

@Composable
fun DetailScreen(
    onNavigateBack: () -> Unit,
    productCode: String?,
    viewModel: ScanViewModel,
    modifier: Modifier = Modifier
) {
    val productResponse by viewModel.product.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var imageUrl by remember { mutableStateOf<String?>(null) }

    if (productCode != null) {
        LaunchedEffect(productCode) {
            viewModel.fetchProductByBarcode(productCode.toString())
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            imageUrl = getImageUrl(productCode)
        }
    } else {
        Text("Arahkan kamera ke barcode")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val product = productResponse!!.product
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUrl != null) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Gambar ${product.productName}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(24.dp)
                            )
                        }
                    },
                )
            } else {
                Box(modifier = Modifier.fillMaxWidth().height(250.dp).align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                    Text("Gambar tidak tersedia", textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nama Produk
            Text(
                text = product.productName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Merek Produk
            if (true) {
                Text(
                    text = product.brandsTags[0],
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            // Informasi Nutrisi
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Informasi Nutrisi (per saji)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    NutrientRow("Energi", product.nutriments.energyKcalServing.toDouble(), "kcal")
                    NutrientRow("Lemak", product.nutriments.fatServing.toDouble(), "g")
                    NutrientRow("   Lemak Jenuh", product.nutriments.saturatedFatServing, "g", isSubEntry = true)
                    NutrientRow("Karbohidrat", product.nutriments.carbohydratesServing.toDouble(), "g")
                    NutrientRow("   Gula", product.nutriments.sugarsServing.toDouble(), "g", isSubEntry = true)
                    NutrientRow("Protein", product.nutriments.proteinsServing.toDouble(), "g")
                    NutrientRow("Serat", product.nutriments.fiberServing.toDouble(), "g")
                    NutrientRow("Garam", product.nutriments.saltServing.toDouble(), "g")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Skor Nutri
            if (product.nutritionGrades != "?") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Skor Nutri: ",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = product.nutritionGrades,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = getNutriScoreColor(product.nutritionGrades)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Kode Produk: ${productResponse!!.code}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun NutrientRow(label: String, value: Double?, unit: String, isSubEntry: Boolean = false) {
    if (value != null && !value.isNaN()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = if (isSubEntry) 16.dp else 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            Text("$value $unit", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun getNutriScoreColor(grade: String): Color {
    return when (grade.uppercase()) {
        "A" -> Color(0xFF008000) // Hijau Tua
        "B" -> Color(0xFF80C000) // Hijau Muda
        "C" -> Color(0xFFFFD700) // Kuning
        "D" -> Color(0xFFFFA500) // Oranye
        "E" -> Color(0xFFFF0000) // Merah
        else -> MaterialTheme.colorScheme.onSurface
    }
}