package com.nutrivision.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.data.remote.response.ProductResponse
import com.nutrivision.app.data.repository.ScanRepository
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

    private val _product = MutableStateFlow<ProductResponse?>(null)
    val product: StateFlow<ProductResponse?> = _product

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val allHistoryItems: StateFlow<List<ScanHistoryItem>> = repository.getAllHistoryItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun fetchProductByBarcode(barcode: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.fetchProductByBarcode(barcode)
                _product.value = response

                response.product.let { productDetails ->
                    if (productDetails.productName.isNotBlank()) {
                        val historyItem = ScanHistoryItem(
                            productCode = response.code.toString(),
                            productName = productDetails.productName,
                            productBrand = productDetails.brandsTags.firstOrNull()?.split(":")
                                ?.getOrNull(1)?.replace("-", " ")?.capitalizeWords()
                                ?: productDetails.brandsTags[0].capitalizeWords().toString(),
                            imageUrl = Utils.getImageUrl(response.code.toString())
                        )
                        repository.insertHistoryItem(historyItem)
                    }
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