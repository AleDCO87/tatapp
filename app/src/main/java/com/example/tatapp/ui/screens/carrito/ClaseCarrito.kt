package com.example.tatapp.ui.screens.carrito

import androidx.compose.runtime.mutableStateListOf
import com.example.tatapp.ui.screens.productos.ClaseProductos

data class ClaseCarrito(
    val producto: ClaseProductos,
    var cantidad: Int
)

val carritoGlobal = mutableStateListOf<ClaseCarrito>()
