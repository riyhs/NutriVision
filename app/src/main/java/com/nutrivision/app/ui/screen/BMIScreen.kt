package com.nutrivision.app.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nutrivision.app.ui.component.GenderSelectorButton
import com.nutrivision.app.ui.theme.NutriVisionTheme
import com.nutrivision.app.utils.BMI.calculateBMI
import com.nutrivision.app.utils.BMI.getBmiCategory
import com.nutrivision.app.utils.Gender


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

    var bmiResult by remember { mutableStateOf<Float?>(null) }
    var showResultDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Body Mass Index (BMI) adalah cara menghitung berat badan ideal berdasarkan tinggi dan berat badan.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
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
                onValueChange = { age = it.filter { char -> char.isDigit() } }, // Hanya izinkan angka
                label = "Age (years)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(
                value = weight,
                onValueChange = { weight = it.filter { char -> char.isDigit() } }, // Hanya izinkan angka
                label = "Weight (kg)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(
                value = height,
                onValueChange = { height = it.filter { char -> char.isDigit() } }, // Hanya izinkan angka
                label = "Height (cm)"
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    val weightValue = weight.toFloatOrNull()
                    val heightValue = height.toFloatOrNull()

                    if (selectedGender != null && weightValue != null && heightValue != null && heightValue > 0) {
                        bmiResult = calculateBMI(weightValue, heightValue)
                        showResultDialog = true
                    } else {
                        Toast.makeText(context, "Harap pilih gender dan isi semua data dengan benar.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showResultDialog) {
            val category = getBmiCategory(bmiResult ?: 0f, selectedGender!!)

            AlertDialog(
                onDismissRequest = { showResultDialog = false },
                title = {
                    Text(text = "Hasil BMI Anda", fontWeight = FontWeight.Bold)
                },
                text = {
                    Column {
                        Text(
                            text = "BMI: ${"%.2f".format(bmiResult)}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kategori: $category",
                            fontSize = 16.sp
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showResultDialog = false }
                    ) {
                        Text("OK")
                    }
                },
                shape = RoundedCornerShape(16.dp)
            )
        }
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

            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),

            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            cursorColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun BMIPreviewScreen(){
//    NutriVisionTheme {
//        BMIScreen(
//            onNavigateBack = {}
//        )
//    }
//}