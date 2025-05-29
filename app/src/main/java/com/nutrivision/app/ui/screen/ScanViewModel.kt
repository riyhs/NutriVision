package com.nutrivision.app.ui.screen

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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchProductByBarcode(barcode: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.fetchProductByBarcode(barcode)
                _product.value = response
            } catch (e: Exception) {
                _product.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}