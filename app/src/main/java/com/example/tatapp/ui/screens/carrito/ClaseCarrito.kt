package com.example.tatapp.ui.screens.carrito

import com.example.tatapp.ui.screens.productos.ClaseProductos

//Clase de cada producto agregado al carrito, la diferencia es que agrega cantidad
data class ClaseCarrito(
    val producto: ClaseProductos,
    var cantidad: Int
)