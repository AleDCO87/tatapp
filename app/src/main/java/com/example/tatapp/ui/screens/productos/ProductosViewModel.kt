package com.example.tatapp.ui.screens.productos

import androidx.lifecycle.ViewModel
import com.example.tatapp.ui.screens.carrito.CarritoViewModel

class ProductosViewModel(
    val carritoViewModel: CarritoViewModel,
    val categoriaSeleccionada: CategoriaProducto?,
    val subcategoriaSeleccionada: String
) : ViewModel() {

    val productosFiltrados: List<ClaseProductos>
        get() = productosBase.filter { producto ->
            (categoriaSeleccionada == null || producto.categoria == categoriaSeleccionada) &&
                    (subcategoriaSeleccionada.isEmpty() || producto.subcategoria == subcategoriaSeleccionada)
        }

    fun agregarAlCarrito(producto: ClaseProductos, cantidad: Int = 1) {
        carritoViewModel.agregarProducto(producto, cantidad)
    }
}