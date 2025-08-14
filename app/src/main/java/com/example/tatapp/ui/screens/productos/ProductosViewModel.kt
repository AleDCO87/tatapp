package com.example.tatapp.ui.screens.productos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tatapp.ui.screens.carrito.CarritoViewModel

class ProductosViewModel(
    val carritoViewModel: CarritoViewModel
) : ViewModel() {

    var categoriaSeleccionada by mutableStateOf<CategoriaProducto?>(null)
        private set

    var selectedCategoriaText by mutableStateOf("Todo")
        private set

    val productosFiltrados: List<ClaseProductos>
        get() = if (categoriaSeleccionada == null) {
            productosBase
        } else {
            productosBase.filter { it.categoria == categoriaSeleccionada }
        }

    fun seleccionarCategoria(categoria: CategoriaProducto?) {
        categoriaSeleccionada = categoria
        selectedCategoriaText = categoria?.displayName ?: "Todo"
    }


    //definido en ClaseCarrito y CarritoViewModel
    fun agregarAlCarrito(producto: ClaseProductos, cantidad: Int = 1) {
        carritoViewModel.agregarProducto(producto, cantidad)
    }
}