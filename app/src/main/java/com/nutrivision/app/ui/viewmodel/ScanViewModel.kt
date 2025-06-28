package com.nutrivision.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.data.remote.response.ProductResponse
import com.nutrivision.app.data.repository.ScanRepository
import com.nutrivision.app.domain.model.Product
import com.nutrivision.app.utils.Utils
import com.nutrivision.app.utils.Utils.capitalizeWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: ScanRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val allHistoryItems: StateFlow<List<Product>> = repository.getAllHistoryItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun fetchProductByBarcode(barcode: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val productDomain = repository.fetchProductByBarcode(barcode)
                _product.value = productDomain

                if (productDomain.name.isNotBlank()) {
                    repository.insertHistoryItem(productDomain)
                }

            } catch (e: Exception) {
                _product.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            repository.clearAllHistory()
        }
    }

    fun deleteHistoryItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteHistoryItemById(itemId)
        }
    }
}