package com.example.tatapp.ui.screens.productos

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tatapp.ui.screens.carrito.ClaseCarrito
import com.example.tatapp.ui.screens.carrito.carritoGlobal
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(navController: NavHostController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Productos", fontSize = 24.sp) },
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
                    selected = false,
                    onClick = { navController.navigate("carrito") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = true,
                    onClick = { /* Ya estás aquí */ }
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productosBase) { producto ->
                ProductoItemRow(producto, snackbarHostState)
            }
        }
    }
}

@Composable
fun ProductoItemRow(producto: ClaseProductos, snackbarHostState: SnackbarHostState) {

    val scope = rememberCoroutineScope()
    var cantidad by remember { mutableStateOf(1) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = producto.imagenRes),
                contentDescription = producto.nombre,
                modifier = Modifier.size(80.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(producto.nombre, fontSize = 22.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(producto.precio, fontSize = 20.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = { if (cantidad > 1) cantidad-- }) { Text("-") }
                    Text("$cantidad", fontSize = 20.sp)
                    Button(onClick = { cantidad++ }) { Text("+") }
                }
            }

            Button(
                onClick = {
                    val existente = carritoGlobal.find { it.producto == producto }
                    if (existente != null) {
                        existente.cantidad += cantidad
                    } else {
                        carritoGlobal.add(ClaseCarrito(producto, cantidad))
                    }
                    scope.launch {
                        snackbarHostState.showSnackbar("${producto.nombre} x$cantidad agregado al carrito")
                    }
                },
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Agregar", fontSize = 20.sp)
            }
        }
    }
}