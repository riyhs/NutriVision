package com.nutrivision.app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nutrivision.app.R
import com.nutrivision.app.ui.theme.NutriVisionTheme

// Warna untuk screen ini
private val profileTextColor = Color(0xFF3EB098)
private val profileLabelColor = Color(0xFF0D473B)

@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = profileBackgroundColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ProfileImage()
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                InfoItem(label = "Username", value = "Amba Timothy")
                Spacer(modifier = Modifier.height(24.dp))
                InfoItem(label = "Bio", value = "Vegetarian and Cannibal")
                Spacer(modifier = Modifier.height(24.dp))
                InfoItem(label = "Email", value = "Ronald@gmail.com")
            }
            Spacer(modifier = Modifier.height(40.dp))

//            COMING SOON
//            ProfileButton(
//                text = "Go to Settings",
//                icon = Icons.Default.Settings,
//                onClick = {  }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            ProfileButton(
//                text = "Log out",
//                icon = Icons.AutoMirrored.Filled.Logout,
//                onClick = {  }
//            )


        }
    }
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.ambaronald),
        contentDescription = "User Avatar",
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(4.dp, Color.White),
                CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape)
    )
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            color = profileLabelColor,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = profileTextColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


// COMING SOON
//@Composable
//fun ProfileButton(
//    text: String,
//    icon: ImageVector,
//    onClick: () -> Unit
//) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        shape = RoundedCornerShape(20.dp),
//        colors = ButtonDefaults.buttonColors(containerColor = profileAccentColor)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = text,
//                tint = Color.White
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(
//                text = text,
//                color = Color.White,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//    }
//}


@Preview (showBackground = true)
@Composable
fun ProfilePreviewScreen() {
    NutriVisionTheme { 
        ProfileScreen(
            onNavigateBack = {}
        )
    }
}