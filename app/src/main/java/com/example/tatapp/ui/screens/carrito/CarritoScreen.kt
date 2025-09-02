package com.example.tatapp.ui.screens.carrito

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tatapp.modelo.entity.CarritoEntity
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavHostController, viewModel: CarritoViewModel) {
    val cs = MaterialTheme.colorScheme
    val carrito by viewModel.carrito.collectAsState()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Carrito", fontSize = 30.sp) }) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = false,
                    onClick = { navController.popBackStack() }
                )
                NavigationBarItem(
                    icon = {
                        BadgedBox(badge = { if (carrito.sumOf { it.cantidad } > 0) Badge { Text("${carrito.sumOf { it.cantidad }}") } }) {
                            Icon(Icons.Rounded.ShoppingCart, contentDescription = "Carrito")
                        }
                    },
                    label = { Text("Carrito", fontSize = 18.sp) },
                    selected = true,
                    onClick = {}
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(8.dp)) {
            if (carrito.isEmpty()) {
                Text("El carrito está vacío", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(carrito, key = { it.id }) { item ->
                        CarritoItemRow(item, viewModel)
                    }
                }

                val totalPrecio = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(viewModel.totalPrecio)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Total: \$$totalPrecio", fontSize = 22.sp, modifier = Modifier.weight(1f))
                    Button(onClick = { /* TODO: pagar */ }, modifier = Modifier.weight(1f).height(50.dp)) {
                        Text("Pagar", fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun CarritoItemRow(item: CarritoEntity, viewModel: CarritoViewModel) {
    val cs = MaterialTheme.colorScheme

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = cs.surfaceVariant,
            contentColor = cs.onSurfaceVariant
        ),
        border = BorderStroke(1.dp, cs.outlineVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = item.imagenRes), contentDescription = item.nombre, modifier = Modifier.size(70.dp))

            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(item.nombre, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledIconButton(onClick = { viewModel.disminuirCantidad(item) }, modifier = Modifier.size(40.dp)) { Text("-", fontSize = 20.sp) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${item.cantidad}", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    FilledIconButton(onClick = { viewModel.aumentarCantidad(item) }, modifier = Modifier.size(40.dp)) { Text("+", fontSize = 20.sp) }
                }
                Spacer(modifier = Modifier.height(4.dp))
                val subtotal = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(item.precio * item.cantidad)
                Text("Subtotal: \$$subtotal", fontSize = 16.sp)
            }

            FilledIconButton(
                onClick = { viewModel.eliminarProducto(item) },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = cs.error,
                    contentColor = cs.onError
                )
            ) { Icon(Icons.Rounded.Delete, contentDescription = "Eliminar producto") }
        }
    }
}
