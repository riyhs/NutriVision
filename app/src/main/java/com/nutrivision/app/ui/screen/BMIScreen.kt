package com.nutrivision.app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Face3
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nutrivision.app.ui.theme.NutriVisionTheme


// Warna untuk screen ini
private val darkGreenAccent = Color(0xFF32B591)
private val genderIconColor = Color(0xFF45B595)
private val genderUnselectedBg = Color(0xFFD4EFE8)
private val darkTextColor = Color(0xFF0D473B)
private val textFieldIndicatorColor = Color(0xFF11755A)
private val textFieldTextColor = Color(0xFF099D7F)

enum class Gender {
    MALE, FEMALE
}

@Composable
fun BMIScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var selectedGender by remember { mutableStateOf<Gender?>(null) }
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = profileBackgroundColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "BMI Calculator",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Body Mass Index (BMI) adalah cara menghitung berat badan ideal berdasarkan tinggi dan berat badan.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GenderSelectorButton(
                    icon = Icons.Default.Face,
                    label = "Male",
                    isSelected = selectedGender == Gender.MALE,
                    onClick = { selectedGender = Gender.MALE }
                )
                GenderSelectorButton(
                    icon = Icons.Default.Face3,
                    label = "Female",
                    isSelected = selectedGender == Gender.FEMALE,
                    onClick = { selectedGender = Gender.FEMALE }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            CustomInputField(
                value = age,
                onValueChange = { age = it },
                label = "Age (years)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(
                value = weight,
                onValueChange = { weight = it },
                label = "Weight (kg)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(
                value = height,
                onValueChange = { height = it },
                label = "Height (cm)"
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = darkGreenAccent)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun GenderSelectorButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) darkGreenAccent else Color.Transparent
    val backgroundColor = if (isSelected) darkGreenAccent.copy(alpha = 0.1f) else genderUnselectedBg

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .border(BorderStroke(2.dp, borderColor), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(50.dp),
                tint = genderIconColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = darkTextColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,

            focusedIndicatorColor = textFieldIndicatorColor,
            unfocusedIndicatorColor = textFieldIndicatorColor.copy(alpha = 0.7f),

            focusedTextColor = textFieldTextColor,
            unfocusedTextColor = textFieldTextColor,
            focusedLabelColor = textFieldIndicatorColor,
            unfocusedLabelColor = textFieldIndicatorColor.copy(alpha = 0.7f),
            cursorColor = textFieldIndicatorColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun BMIPreviewScreen(){
    NutriVisionTheme {
        BMIScreen(
            onNavigateBack = {}
        )
    }
}