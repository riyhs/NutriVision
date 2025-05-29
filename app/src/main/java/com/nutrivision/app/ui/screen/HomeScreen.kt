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
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.nutrivision.app.ui.theme.NutriVisionTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.nutrivision.app.R


val profileBackgroundColor = Color(0xFFD6FFFF)
val mediumGreen = Color(0xFF52C6A7)
val darkGreen = Color(0xFF32B591)
val textGray = Color(0xFF6B7280)

@Composable
fun HomeScreen(
    onNavigateToHistory: () -> Unit,
    onNavigateToBMI: () -> Unit,
    onNavigateToScan: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = profileBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            Header()
            Spacer(modifier = Modifier.height(24.dp))

            BMIDisplayCard()
            Spacer(modifier = Modifier.height(24.dp))

            ScanButton(onNavigateToScan = onNavigateToScan)
            Spacer(modifier = Modifier.height(24.dp))

            ActionButtons(onNavigateToBMI = onNavigateToBMI,onNavigateToHistory = onNavigateToHistory)
            Spacer(modifier = Modifier.height(24.dp))

            NutritionTipCard()

        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Hello, Timothy!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D473B)
            )
            Text(
                text = "Tap. Scan. Stay healthy.",
                fontSize = 16.sp,
                color = textGray
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun BMIDisplayCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = mediumGreen),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Text(
                text = "Your Health Overview",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                MetricDisplay(
                    value = "23.5",
                    label = "Current BMI",
                    subLabel = "Normal Weight",
                    modifier = Modifier.weight(1f),
                )

                MetricDisplay(
                    value = "75 kg",
                    label = "Current Weight",
                    subLabel = "",
                    modifier = Modifier.weight(1f),
                    valueFontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Height: 178 cm  ·  Updated 2 days ago",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
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
        horizontalAlignment = Alignment.Start // Teks rata kiri
    ) {

        Text(
            text = value,
            color = Color.White,
            fontSize = valueFontSize,
            fontWeight = FontWeight.Bold,
            lineHeight = (valueFontSize.value * 1.1).sp
        )

        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        if (subLabel.isNotEmpty()) {
            Text(
                text = subLabel,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun ScanButton(
    onNavigateToScan: () -> Unit
) {
    Button(
        onClick = {  onNavigateToScan()  },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
    ) {
        Icon(
            imageVector = Icons.Filled.PhotoCamera ,
            contentDescription = "Scan Icon",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Scan now!",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
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
            text = "Calculate BMI",
            icon = Icons.Filled.Calculate,
            onClick = { onNavigateToBMI() },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        ActionButton(
            text = "View History",
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
        colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NutritionTipCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = darkGreen.copy(alpha = 0.8f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Lightbulb,
                contentDescription = "Nutrition Tip",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Daily Nutrition Tip",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = "Eating colorful vegetables ensures a wide range of nutrients",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NutriVisionTheme {
        HomeScreen(
            onNavigateToHistory = {},
            onNavigateToBMI = {},
            onNavigateToScan = {}
        )
    }

}