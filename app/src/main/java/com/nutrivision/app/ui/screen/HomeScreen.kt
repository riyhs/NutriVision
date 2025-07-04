package com.nutrivision.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import coil.compose.rememberAsyncImagePainter
import com.nutrivision.app.R
import com.nutrivision.app.domain.model.User
import com.nutrivision.app.ui.viewmodel.AuthViewModel
import com.nutrivision.app.utils.BMI.calculateBMI
import com.nutrivision.app.utils.BMI.getBmiCategory
import com.nutrivision.app.utils.Gender
import com.nutrivision.app.utils.NutritionTips


@Composable
fun HomeScreen(
    onNavigateToHistory: () -> Unit,
    onNavigateToBMI: () -> Unit,
    onNavigateToScan: () -> Unit,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val user by authViewModel.user.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 0.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Header(user)
            Spacer(modifier = Modifier.height(24.dp))

            BMIDisplayCard(user)
            Spacer(modifier = Modifier.height(24.dp))

            ActionButtons(onNavigateToBMI = onNavigateToBMI,onNavigateToHistory = onNavigateToHistory)
            Spacer(modifier = Modifier.height(24.dp))

            NutritionTipCard(user)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun Header(user: User?) {
    val painter = if (user?.photoUrl != null) {
        rememberAsyncImagePainter(user.photoUrl)
    } else {
        painterResource(id = R.drawable.profile)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.7.toFloat()),
        ) {
            Text(
                text = "Halo, ${user?.displayName ?: "Nutrifans"}!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Cek Gizi, Sehat Pasti.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }
        Image(
            painter = painter,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun BMIDisplayCard(user: User?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Text(
                text = "Ringkasan Kesehatan",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                var bmi = 0.0.toFloat()
                var category = "Tidak ada data"

                if (user != null && user.weight != 0.toFloat() && user.height != 0.toFloat() && user.gender != null) {
                    if (user.gender != Gender.NODATA) {
                        bmi = calculateBMI(user.weight, user.height)
                        category = getBmiCategory(bmi, user.gender)
                    }
                }
                MetricDisplay(
                    value = bmi.toString(),
                    label = "BMI Saat ini",
                    subLabel = category,
                    modifier = Modifier.weight(1f),
                )

                MetricDisplay(
                    value = user?.weight.toString() + " kg",
                    label = "Berat Badanmu",
                    subLabel = "",
                    modifier = Modifier.weight(1f),
                    valueFontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Tinggi badan: ${user?.height} cm",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MetricDisplay(
    value: String,
    label: String,
    subLabel: String,
    modifier: Modifier = Modifier,
    valueFontSize: TextUnit = 44.sp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = valueFontSize,
            fontWeight = FontWeight.Bold,
            lineHeight = (valueFontSize.value * 1.1).sp
        )

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        if (subLabel.isNotEmpty()) {
            Text(
                text = subLabel,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ActionButtons(
    onNavigateToBMI: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            text = "Kalkulator BMI",
            icon = Icons.Filled.Calculate,
            onClick = { onNavigateToBMI() },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        ActionButton(
            text = "Riwayat Scan",
            icon = Icons.Filled.History,
            onClick = { onNavigateToHistory() },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NutritionTipCard(user: User?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Lightbulb,
                contentDescription = "Nutrition Tip",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Tips Nutrisi Harian",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 16.sp
                )

                var tipsText = "Konsumsi sayuran untuk memastikan kebutuhan nutrisi tercukupi"
                if (user != null && user.weight != 0.toFloat() && user.height != 0.toFloat() && user.gender != null) {
                    val bmiCategory = getBmiCategory(calculateBMI(user.weight, user.height), user.gender)
                    tipsText = NutritionTips.getRandomTipBasedOnBMI(bmiCategory)
                }

                Text(
                    text = tipsText,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    NutriVisionTheme {
//        HomeScreen(
//            onNavigateToHistory = {},
//            onNavigateToBMI = {},
//            onNavigateToScan = {}
//        )
//    }
//
//}