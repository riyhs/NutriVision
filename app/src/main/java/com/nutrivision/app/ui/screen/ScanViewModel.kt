package com.nutrivision.app.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrivision.app.data.remote.response.ProductResponse
import com.nutrivision.app.data.repository.ScanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: ScanRepository
) : ViewModel() {

    private val _product = MutableStateFlow<ProductResponse?>(null)
    val product: StateFlow<ProductResponse?> = _product

    fun fetchProductByBarcode(barcode: String) {
        Log.d("ScanViewModel", "Fetch")

        viewModelScope.launch {
            try {
                val response = repository.fetchProductByBarcode(barcode)
                _product.value = response
                Log.d("ScanViewModel", "Product response: $response")
            } catch (e: Exception) {
                _product.value = null
                Log.d("ScanViewModel", "Error: ${e.message.toString()}")
            }
        }
    }
}