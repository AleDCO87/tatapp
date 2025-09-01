package com.example.tatapp.ui.screens.productos


import com.example.tatapp.R

// Enum para categorías generales
enum class CategoriaProducto(val displayName: String) {
    ALIMENTOS("Alimentos"),
    SALUD("Salud"),
    MASCOTAS("Mascotas"),
    HOGAR("Hogar"),
    JARDIN("Jardín"),
    BELLEZA("Belleza"),
    REGALOS("Regalos"),
    CUIDADOS("Cuidados"),
    VIAJES("Viajes"),
    PANORAMAS("Panoramas")
}

// Clase de producto ahora incluye subcategoría
data class ClaseProductos(
    val nombre: String,
    val precio: Int,
    val imagenRes: Int,
    val categoria: CategoriaProducto,
    val subcategoria: String,
    val evaluacion: Float
)

// Lista de productos base
val productosBase = listOf(
    // ALIMENTOS
    ClaseProductos("Manzanas", 2000, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Frutas y Verduras", 4.5f),
    ClaseProductos("Palta", 1500, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Frutas y Verduras", 4.2f),
    ClaseProductos("Nueces", 2000, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Frutas y Verduras", 4.7f),

    ClaseProductos("Garbanzos", 1800, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Cereales", 4.1f),
    ClaseProductos("Avena", 2500, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Cereales", 4.3f),
    ClaseProductos("Lentejas", 2000, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Cereales", 4.0f),

    ClaseProductos("Huevos", 2500, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Lácteos", 4.6f),
    ClaseProductos("Leche", 1800, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Lácteos", 4.4f),
    ClaseProductos("Yogur griego", 2000, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Lácteos", 4.8f),

    ClaseProductos("Pescado graso", 3200, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Carnes", 4.9f),
    ClaseProductos("Pollo", 2800, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Carnes", 4.6f),
    ClaseProductos("Carne de vacuno", 3500, R.drawable.ej_alim, CategoriaProducto.ALIMENTOS, "Carnes", 4.7f),

    // SALUD
    ClaseProductos("Jabón", 1200, R.drawable.ej_alim, CategoriaProducto.SALUD, "Higiene", 4.3f),
    ClaseProductos("Cepillo dental", 800, R.drawable.ej_alim, CategoriaProducto.SALUD, "Higiene", 4.5f),
    ClaseProductos("Shampoo", 1500, R.drawable.ej_alim, CategoriaProducto.SALUD, "Higiene", 4.2f),

    ClaseProductos("Vitamina C", 2500, R.drawable.ej_alim, CategoriaProducto.SALUD, "Suplementos", 4.6f),
    ClaseProductos("Omega 3", 3000, R.drawable.ej_alim, CategoriaProducto.SALUD, "Suplementos", 4.8f),
    ClaseProductos("Magnesio", 2200, R.drawable.ej_alim, CategoriaProducto.SALUD, "Suplementos", 4.4f),

    ClaseProductos("Paracetamol", 1800, R.drawable.ej_alim, CategoriaProducto.SALUD, "Medicamentos", 4.5f),
    ClaseProductos("Ibuprofeno", 2000, R.drawable.ej_alim, CategoriaProducto.SALUD, "Medicamentos", 4.3f),
    ClaseProductos("Amoxicilina", 2500, R.drawable.ej_alim, CategoriaProducto.SALUD, "Medicamentos", 4.7f),

    // MASCOTAS
    ClaseProductos("Comida Gato", 3000, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Gato", 4.6f),
    ClaseProductos("Juguete Gato", 1200, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Gato", 4.4f),
    ClaseProductos("Arena Gato", 2000, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Gato", 4.5f),

    ClaseProductos("Comida Perro", 3500, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Perro", 4.7f),
    ClaseProductos("Collar Perro", 1500, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Perro", 4.6f),
    ClaseProductos("Juguete Perro", 1800, R.drawable.ej_alim, CategoriaProducto.MASCOTAS, "Perro", 4.5f),

    // HOGAR
    ClaseProductos("Silla de comedor", 45000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Muebles", 4.6f),
    ClaseProductos("Mesa de centro", 55000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Muebles", 4.7f),
    ClaseProductos("Estantería", 60000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Muebles", 4.5f),

    ClaseProductos("Taladro", 25000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Herramientas", 4.8f),
    ClaseProductos("Destornillador set", 12000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Herramientas", 4.6f),
    ClaseProductos("Sierra manual", 15000, R.drawable.ej_alim, CategoriaProducto.HOGAR, "Herramientas", 4.4f),

    // JARDÍN
    ClaseProductos("Planta interior", 8000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Plantas", 4.7f),
    ClaseProductos("Planta exterior", 10000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Plantas", 4.6f),
    ClaseProductos("Suculenta", 5000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Plantas", 4.5f),

    ClaseProductos("Guantes de jardinería", 3000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Accesorios", 4.6f),
    ClaseProductos("Manguera", 7000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Accesorios", 4.5f),
    ClaseProductos("Regadera", 4500, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Accesorios", 4.4f),

    ClaseProductos("Fertilizante orgánico", 12000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Fertilizantes", 4.7f),
    ClaseProductos("Fertilizante líquido", 10000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Fertilizantes", 4.6f),
    ClaseProductos("Abono granular", 8000, R.drawable.ej_alim, CategoriaProducto.JARDIN, "Fertilizantes", 4.5f),

    // BELLEZA
    ClaseProductos("Labial", 7000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Maquillaje", 4.8f),
    ClaseProductos("Base de maquillaje", 12000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Maquillaje", 4.7f),
    ClaseProductos("Rubor", 9000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Maquillaje", 4.5f),

    ClaseProductos("Perfume femenino", 35000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Perfumería", 4.8f),
    ClaseProductos("Perfume masculino", 37000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Perfumería", 4.7f),
    ClaseProductos("Crema corporal", 15000, R.drawable.ej_alim, CategoriaProducto.BELLEZA, "Perfumería", 4.6f),

    // REGALOS
    ClaseProductos("Cartera mujer", 25000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Ella", 4.7f),
    ClaseProductos("Collar mujer", 18000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Ella", 4.6f),
    ClaseProductos("Set maquillaje", 30000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Ella", 4.8f),

    ClaseProductos("Reloj hombre", 40000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Él", 4.7f),
    ClaseProductos("Cinturón hombre", 15000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Él", 4.5f),
    ClaseProductos("Set afeitado", 22000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Él", 4.6f),

    ClaseProductos("Muñeco niño", 12000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niño", 4.6f),
    ClaseProductos("Libro infantil", 8000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niño", 4.5f),
    ClaseProductos("Set de construcción", 15000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niño", 4.7f),

    ClaseProductos("Muñeca niña", 12000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niña", 4.6f),
    ClaseProductos("Puzzle educativo", 9000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niña", 4.5f),
    ClaseProductos("Set de arte", 14000, R.drawable.ej_alim, CategoriaProducto.REGALOS, "Niña", 4.7f),

    // CUIDADOS
    ClaseProductos("Masaje relajante", 20000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Terapias", 4.8f),
    ClaseProductos("Acupuntura", 25000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Terapias", 4.7f),
    ClaseProductos("Reflexología", 18000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Terapias", 4.6f),

    ClaseProductos("Limpieza facial", 12000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Servicios", 4.7f),
    ClaseProductos("Depilación", 15000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Servicios", 4.6f),
    ClaseProductos("Manicure", 10000, R.drawable.ej_alim, CategoriaProducto.CUIDADOS, "Servicios", 4.5f),

    // VIAJES
    ClaseProductos("Tour Santiago", 50000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Nacional", 4.8f),
    ClaseProductos("Tour Valparaíso", 45000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Nacional", 4.7f),
    ClaseProductos("Excursión Patagonia", 80000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Nacional", 4.9f),

    ClaseProductos("Tour París", 120000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Internacional", 4.9f),
    ClaseProductos("Tour Roma", 115000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Internacional", 4.8f),
    ClaseProductos("Tour Nueva York", 130000, R.drawable.ej_alim, CategoriaProducto.VIAJES, "Internacional", 5.0f),

    // PANORAMAS
    ClaseProductos("Picnic parque", 15000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Comidas", 4.7f),
    ClaseProductos("Asado familiar", 25000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Comidas", 4.8f),
    ClaseProductos("Brunch amigos", 20000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Comidas", 4.6f),

    ClaseProductos("Caminata bosque", 5000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Aire libre", 4.7f),
    ClaseProductos("Tour bicicleta", 7000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Aire libre", 4.8f),
    ClaseProductos("Camping", 12000, R.drawable.ej_alim, CategoriaProducto.PANORAMAS, "Aire libre", 4.9f)
)