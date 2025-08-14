package com.example.tatapp.ui.screens.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tatapp.ui.screens.carrito.CarritoViewModel

class ProductosViewModelFactory(
    private val carritoViewModel: CarritoViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductosViewModel(carritoViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
