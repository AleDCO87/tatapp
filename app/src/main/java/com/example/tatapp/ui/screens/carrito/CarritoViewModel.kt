package com.example.tatapp.ui.screens.carrito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tatapp.modelo.dao.CarritoDao
import com.example.tatapp.modelo.entity.CarritoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(private val dao: CarritoDao) : ViewModel() {

    private val _carrito = MutableStateFlow<List<CarritoEntity>>(emptyList())
    val carrito: StateFlow<List<CarritoEntity>> = _carrito.asStateFlow()

    val totalPrecio: Int get() = _carrito.value.sumOf { it.precio * it.cantidad }

    init {
        viewModelScope.launch {
            dao.obtenerCarrito().collect { _carrito.value = it }
        }
    }

    fun agregarAlCarrito(
        id: String,
        nombre: String,
        precio: Int,
        cantidad: Int,
        imagenRes: Int
    ) = viewModelScope.launch {
        val existente = dao.getById(id)
        if (existente != null) {
            dao.actualizarProducto(existente.copy(cantidad = existente.cantidad + cantidad))
        } else {
            dao.insertarProducto(
                CarritoEntity(
                    id = id,
                    nombre = nombre,
                    precio = precio,
                    cantidad = cantidad,
                    imagenRes = imagenRes
                )
            )
        }
    }

    fun aumentarCantidad(producto: CarritoEntity) = viewModelScope.launch {
        dao.actualizarProducto(producto.copy(cantidad = producto.cantidad + 1))
    }

    fun disminuirCantidad(producto: CarritoEntity) = viewModelScope.launch {
        if (producto.cantidad > 1) dao.actualizarProducto(producto.copy(cantidad = producto.cantidad - 1))
        else dao.eliminarProducto(producto)
    }

    fun eliminarProducto(producto: CarritoEntity) = viewModelScope.launch {
        dao.eliminarProducto(producto)
    }

    fun vaciarCarrito() = viewModelScope.launch { dao.vaciarCarrito() }
}
