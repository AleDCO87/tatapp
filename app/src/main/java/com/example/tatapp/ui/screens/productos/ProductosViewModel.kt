package com.example.tatapp.ui.screens.productos

import androidx.lifecycle.ViewModel

class ProductosViewModel(
    val categoriaSeleccionada: CategoriaProducto?,
    val subcategoriaSeleccionada: String
) : ViewModel() {

    val productosFiltrados: List<ClaseProductos>
        get() = productosBase.filter { producto ->
            (categoriaSeleccionada == null || producto.categoria == categoriaSeleccionada) &&
                    (subcategoriaSeleccionada.isEmpty() || producto.subcategoria == subcategoriaSeleccionada)
        }
}
