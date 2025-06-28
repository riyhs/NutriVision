package com.nutrivision.app.ui.screen

import android.Manifest
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.nutrivision.app.data.remote.response.ProductResponse.Product
import com.nutrivision.app.ui.viewmodel.ScanViewModel
import com.nutrivision.app.utils.Utils.getImageUrl
import java.util.concurrent.Executors


@Composable
fun ScanScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: ScanViewModel,
    modifier: Modifier = Modifier
) {
    var scannedValue by remember { mutableStateOf<String?>(null) }
    val productResponse by viewModel.product.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            BarcodeScannerScreen { barcode ->
                if (barcode != null) {
                    scannedValue = barcode
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (scannedValue != null) {
                LaunchedEffect(scannedValue) {
                    viewModel.fetchProductByBarcode(scannedValue.toString())
                }
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    productResponse?.let { product ->
                        ProductCard(
                            product = product.product,
                            productImageUrl = getImageUrl(product.code),
                            onClick = {
                                onNavigateToDetail(product.code.toString())
                            }
                        )
                    } ?: Text("Informasi produk tidak tersedia")
                }
            } else {
                Text("Arahkan kamera ke barcode")
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BarcodeScannerScreen(
    modifier: Modifier = Modifier,
    onBarcodeScanned: (String?) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }

    if (cameraPermissionState.status.isGranted) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        ) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }

                    val rotation = ctx.display.rotation

                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .setTargetRotation(rotation)
                        .build()
                        .also {
                            it.surfaceProvider = previewView.surfaceProvider
                        }

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .setTargetRotation(rotation)
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor, BarcodeAnalyzer(context) { barcodeResult ->
                                if (barcodeResult != null) {
                                    onBarcodeScanned(barcodeResult)
                                } else {
                                    onBarcodeScanned(null)
                                }
                            })
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageAnalyzer
                        )
                    } catch (exc: Exception) {
                        Log.e("BarcodeScanner", "Gagal melakukan binding pada kasus penggunaan kamera", exc)
                        onBarcodeScanned(null)
                    }
                    previewView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(24.dp))
                    .align(Alignment.Center)
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Izin kamera diperlukan untuk memindai kode batang.")
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Berikan Izin Kamera")
            }
        }
    }
}

fun rememberPermissionState(camera: String) {

}

private class BarcodeAnalyzer(
    private val context: Context,
    private val onBarcodeDetected: (String?) -> Unit
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC,
            Barcode.FORMAT_CODE_128,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_UPC_A
        )
        .build()

    private val scanner = BarcodeScanning.getClient(options)
    private var lastScannedTimestamp = 0L
    private val debounceMs = 1000

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val currentTime = System.currentTimeMillis()

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        val barcode = barcodes[0]
                        val rawValue = barcode.rawValue

                        if (rawValue != null && currentTime - lastScannedTimestamp > debounceMs) {
                            Log.d("BarcodeAnalyzer", "Barcode found: $rawValue")
                            onBarcodeDetected(rawValue)
                            lastScannedTimestamp = currentTime
                        } else if (rawValue == null) {
                            onBarcodeDetected(null)
                        }
                    } else {
                        onBarcodeDetected(null)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("BarcodeAnalyzer", "Gagal memproses gambar", e)
                    onBarcodeDetected(null)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}

@Composable
fun ProductCard(product: Product, productImageUrl: String?, onClick: () -> Unit) {
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
                    .data(productImageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
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
                contentDescription = "Image of ${product.productName}",
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = product.brandsTags[0],
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}