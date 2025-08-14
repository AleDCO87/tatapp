package com.example.tatapp.ui.screens.carrito

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carrito", fontSize = 24.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Abrir menú */ }) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Ir al perfil */ }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.ShoppingCart, contentDescription = "Carrito") },
                    label = { Text("Carrito", fontSize = 18.sp) },
                    selected = true,
                    onClick = { /* Ya estás aquí */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = false,
                    onClick = { navController.navigate("productos") }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            if (carritoGlobal.isEmpty()) {
                Text("El carrito está vacío", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(carritoGlobal) { item -> CarritoItemRow(item) }
                }

                val total = carritoGlobal.sumOf { item ->
                    val precioNumero = item.producto.precio.replace("[^\\d]".toRegex(), "").toInt()
                    precioNumero * item.cantidad
                }

                Text("Total: \$${total}", fontSize = 22.sp, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun CarritoItemRow(item: ClaseCarrito) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = item.producto.imagenRes),
                contentDescription = item.producto.nombre,
                modifier = Modifier.size(64.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(item.producto.nombre, fontSize = 20.sp, color = Color.Black)
                Text("Cantidad: ${item.cantidad}", fontSize = 18.sp, color = Color.DarkGray)
                val precioNumero = item.producto.precio.replace("[^\\d]".toRegex(), "").toInt()
                Text("Subtotal: \$${precioNumero * item.cantidad}", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}