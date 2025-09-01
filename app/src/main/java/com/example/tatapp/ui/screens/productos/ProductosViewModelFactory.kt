package com.example.tatapp.ui.screens.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tatapp.ui.screens.carrito.CarritoViewModel

class ProductosViewModelFactory(
    private val carritoViewModel: CarritoViewModel,
    private val categoria: String?,
    private val subcategoria: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            val categoriaEnum = try {
                categoria?.let { CategoriaProducto.valueOf(it.uppercase()) }
            } catch (e: IllegalArgumentException) { null }

            @Suppress("UNCHECKED_CAST")
            return ProductosViewModel(
                carritoViewModel,
                categoriaEnum,
                subcategoria ?: ""
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}