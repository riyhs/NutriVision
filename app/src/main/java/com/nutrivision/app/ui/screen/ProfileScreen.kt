package com.nutrivision.app.ui.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.nutrivision.app.R
import com.nutrivision.app.data.model.UserProfile
import com.nutrivision.app.ui.theme.NutriVisionTheme
import com.nutrivision.app.ui.viewmodel.AuthState
import com.nutrivision.app.ui.viewmodel.AuthViewModel


@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val localContext = LocalContext.current
    val authState by authViewModel.authState.collectAsState()
    val userProfile by authViewModel.userProfile.collectAsState()

    // Image picker
    val cropOptions = CropImageOptions(
        aspectRatioX = 1,
        aspectRatioY = 1,
        fixAspectRatio = true,
        imageSourceIncludeGallery = true,
        imageSourceIncludeCamera = true,
        outputCompressQuality = 80,
        guidelines = CropImageView.Guidelines.ON,
        cornerShape = CropImageView.CropCornerShape.OVAL, // Or RECTANGLE
        cropShape = CropImageView.CropShape.OVAL // Or RECTANGLE
    )

    val cropImageLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let { uri ->
                authViewModel.uploadProfileImage(uri)
            }
        } else {
            val exception = result.error
            Toast.makeText(localContext, "Image cropping failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Unauthenticated -> onNavigateToLogin()
            is AuthState.Error -> {
                Toast.makeText(
                    localContext,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> Unit
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ProfileImage(
                userProfile,
                profileImageClicked = {
                    val contractOptions = CropImageContractOptions(uri = null, cropImageOptions = cropOptions)
                    cropImageLauncher.launch(contractOptions)
                }
            )
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                InfoItem(label = "Name", value = userProfile?.displayName ?: "Loading...")
                Spacer(modifier = Modifier.height(24.dp))
                InfoItem(label = "Email", value = userProfile?.email ?: "Loading...")
                Spacer(modifier = Modifier.height(24.dp))
            }
            Spacer(modifier = Modifier.height(40.dp))

            ProfileButton(
                text = "Edit Profile",
                icon = Icons.Default.Edit,
                onClick =  { onNavigateToEditProfile() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))
            ProfileButton(
                text = "Log out",
                icon = Icons.AutoMirrored.Filled.Logout,
                onClick = {
                    authViewModel.logout()
                },
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )


        }
    }
}

@Composable
fun ProfileImage(
    userProfile: UserProfile?,
    profileImageClicked: () -> Unit
) {
    val painter = if (userProfile?.photoUrl != null) {
        rememberAsyncImagePainter(userProfile.photoUrl)
    } else {
        painterResource(id = R.drawable.ambaronald)
    }

    Image(
        painter = painter,
        contentDescription = "User Avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape)
            .clickable {
                profileImageClicked()
            }
    )
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ProfileButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

    }
}
