package com.example.tatapp.ui.screens.productos

import com.example.tatapp.R

data class ClaseProductos(
    val nombre: String,
    val precio: String,
    val imagenRes: Int
)

val productosBase = listOf(
    ClaseProductos("Manzanas", "$2.000", R.drawable.ic_launcher_background),
    ClaseProductos("Pan integral", "$1.500", R.drawable.ic_launcher_background),
    ClaseProductos("Leche descremada", "$1.800", R.drawable.ic_launcher_background),
    ClaseProductos("Cereal", "$3.200", R.drawable.ic_launcher_background),
    ClaseProductos("Manzanas", "$2.000", R.drawable.ic_launcher_background),
    ClaseProductos("Pan integral", "$1.500", R.drawable.ic_launcher_background),
    ClaseProductos("Leche descremada", "$1.800", R.drawable.ic_launcher_background),
    ClaseProductos("Cereal", "$3.200", R.drawable.ic_launcher_background),
    ClaseProductos("Manzanas", "$2.000", R.drawable.ic_launcher_background),
    ClaseProductos("Pan integral", "$1.500", R.drawable.ic_launcher_background),
    ClaseProductos("Leche descremada", "$1.800", R.drawable.ic_launcher_background),
    ClaseProductos("Cereal", "$3.200", R.drawable.ic_launcher_background)
)