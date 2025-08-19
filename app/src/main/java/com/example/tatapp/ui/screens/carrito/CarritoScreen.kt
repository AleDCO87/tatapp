package com.example.tatapp.ui.screens.carrito

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavHostController, viewModel: CarritoViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carrito", fontSize = 30.sp) },
                /*
                navigationIcon = {
                    IconButton(onClick = { /* Abrir menú */ }) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menú")
                    }
                },
                */
                /*
                actions = {
                    IconButton(onClick = { /* Ir al perfil */ }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
                    }
                }*/    //para despues
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = false,
                    onClick = { navController.navigate("productos") }
                )

                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (viewModel.totalEnCarrito > 0) {
                                    Badge { Text(viewModel.totalEnCarrito.toString()) }
                                }
                            }
                        ) {
                            Icon(Icons.Rounded.ShoppingCart, contentDescription = "Carrito")
                        }
                    },
                    label = { Text("Carrito", fontSize = 18.sp) },
                    selected = true,
                    onClick = { /* Ya estás aquí */ }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(8.dp)) {
            if (viewModel.carrito.isEmpty()) {
                Text("El carrito está vacío", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(viewModel.carrito) { item ->
                        CarritoItemRow(item, viewModel)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,

                ){
                    Text(
                        "Total: \$${NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(viewModel.totalPrecio)}",
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    )
                    Button(
                        onClick = { /* Acción para pagar */ },
                        modifier = Modifier.fillMaxWidth().weight(1f).height(50.dp)
                    ) { Text("Pagar", fontSize = 20.sp) }

                }


            }
        }
    }
}

@Composable
fun CarritoItemRow(item: ClaseCarrito, viewModel: CarritoViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        border = BorderStroke(1.dp, Color.Gray)
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
                modifier = Modifier.size(70.dp)
            )

            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(item.producto.nombre, fontSize = 22.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Row (verticalAlignment= Alignment.CenterVertically,) {
                    FilledIconButton(
                        onClick = { viewModel.disminuirCantidad(item) },
                        modifier = Modifier.size(40.dp)
                    ) { Text("-", fontSize = 20.sp) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${item.cantidad}", fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    FilledIconButton(
                        onClick = { viewModel.aumentarCantidad(item) },
                        modifier = Modifier.size(40.dp)
                    ) { Text("+", fontSize = 20.sp) }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Subtotal: \$${NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(item.producto.precio * item.cantidad)}",
                   // "Subtotal: \$${"%,d".format(item.producto.precio * item.cantidad)}",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            FilledIconButton(
                onClick = { viewModel.eliminarProducto(item) },
                colors= IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Rounded.Delete, contentDescription = "Eliminar producto")
            }
        }
    }
}