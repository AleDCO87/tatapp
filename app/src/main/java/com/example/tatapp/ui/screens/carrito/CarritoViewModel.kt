package com.example.tatapp.ui.screens.carrito

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tatapp.ui.screens.productos.ClaseProductos

class CarritoViewModel : ViewModel() {

    val carrito = mutableStateListOf<ClaseCarrito>()

    // Última categoría y subcategoría agregadas
    var ultimaCategoria: String? = null
    var ultimaSubcategoria: String? = null

    val totalEnCarrito: Int
        get() = carrito.sumOf { it.cantidad }

    val totalPrecio: Int
        get() = carrito.sumOf { it.producto.precio * it.cantidad }

    fun agregarProducto(producto: ClaseProductos, cantidad: Int = 1) {
        // Guardamos categoría y subcategoría
        ultimaCategoria = producto.categoria.name
        ultimaSubcategoria = producto.subcategoria

        val existente = carrito.find { it.producto == producto }
        if (existente != null) {
            existente.cantidad += cantidad
        } else {
            carrito.add(ClaseCarrito(producto, cantidad))
        }
    }

    fun aumentarCantidad(item: ClaseCarrito) {
        item.cantidad++
    }

    fun disminuirCantidad(item: ClaseCarrito) {
        if (item.cantidad > 1) {
            item.cantidad--
        } else {
            eliminarProducto(item)
        }
    }

    fun eliminarProducto(item: ClaseCarrito) {
        carrito.remove(item)
    }
}