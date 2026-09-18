package com.varsha.productcatalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varsha.productcatalog.data.Product
import com.varsha.productcatalog.data.ProductRepository
import com.varsha.productcatalog.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository =
        ProductRepository(RetrofitInstance.api)

    private val _products =
        MutableStateFlow<List<Product>>(emptyList())

    val products: StateFlow<List<Product>> =
        _products.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> =
        _error.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                val response = repository.getProducts(
                    limit = 20,
                    skip = 0
                )

                _products.value = response.products

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Something went wrong"

            } finally {

                _isLoading.value = false
            }
        }
    }
}
