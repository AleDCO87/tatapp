package com.example.tatapp.ui.screens.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductosViewModelFactory(
    private val categoriaSeleccionada: CategoriaProducto?,
    private val subcategoriaSeleccionada: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            return ProductosViewModel(categoriaSeleccionada, subcategoriaSeleccionada) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
