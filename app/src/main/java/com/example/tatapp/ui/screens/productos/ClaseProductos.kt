package com.example.tatapp.ui.screens.productos

import com.example.tatapp.R

// Enum para categorías
enum class CategoriaProducto(val displayName: String) {
    SINGLUTEN("Sin gluten"),
    SINAZUCAR("Sin azucar"),
    HIPERTENSION("Hipertensión")
}

// Class de cada producto
data class ClaseProductos(
    val nombre: String,
    val precio: Int,
    val imagenRes: Int,
    val categoria: CategoriaProducto
)

// Lista de productos base
val productosBase = listOf(
    ClaseProductos("Manzanas", 2000, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Palta", 1500, R.drawable.ej_alim, CategoriaProducto.SINAZUCAR),
    ClaseProductos("Garbanzos", 1800, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Pescado graso", 3200, R.drawable.ej_alim, CategoriaProducto.SINAZUCAR),
    ClaseProductos("Plátanos", 2500, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Espinacas", 1700, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Nueces", 2000, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Naranjas", 1500, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Yogur griego", 1800, R.drawable.ej_alim, CategoriaProducto.SINAZUCAR),
    ClaseProductos("Avena", 3200, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Huevos", 2500, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Brócoli", 1700, R.drawable.ej_alim, CategoriaProducto.SINAZUCAR),
    ClaseProductos("Lentejas", 2000, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Chocolate negro", 1500, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Pollo", 1800, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Aceite de oliva", 3200, R.drawable.ej_alim, CategoriaProducto.HIPERTENSION),
    ClaseProductos("Almendras", 2500, R.drawable.ej_alim, CategoriaProducto.SINGLUTEN),
    ClaseProductos("Ajos", 1700, R.drawable.ej_alim, CategoriaProducto.SINAZUCAR)
)