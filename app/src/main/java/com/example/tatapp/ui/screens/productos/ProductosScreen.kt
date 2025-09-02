package com.example.tatapp.ui.screens.productos

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tatapp.ui.screens.carrito.CarritoViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    navController: NavHostController,
    carritoViewModel: CarritoViewModel,
    categoria: String,
    subcategoria: String
) {
    val productosViewModel: ProductosViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProductosViewModelFactory(
            categoriaSeleccionada = CategoriaProducto.values().find { it.name.equals(categoria, ignoreCase = true) },
            subcategoriaSeleccionada = Uri.decode(subcategoria)
        )
    )

    val carrito by carritoViewModel.carrito.collectAsState()
    val totalEnCarrito = carrito.sumOf { it.cantidad }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope() // ← usamos scope en vez de LaunchedEffect dentro de callbacks

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Productos", fontSize = 30.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = {
                        BadgedBox(badge = { if (totalEnCarrito > 0) Badge { Text("$totalEnCarrito") } }) {
                            Icon(Icons.Rounded.ShoppingCart, contentDescription = "Carrito")
                        }
                    },
                    label = { Text("Carrito", fontSize = 18.sp) },
                    selected = false,
                    onClick = { navController.navigate("carrito") }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = productosViewModel.productosFiltrados, key = { it.id }) { producto ->
                ProductoItemRow(
                    producto = producto,
                    onAgregar = { cantidad ->
                        carritoViewModel.agregarAlCarrito(
                            id = producto.id,
                            nombre = producto.nombre,
                            precio = producto.precio,
                            cantidad = cantidad,
                            imagenRes = producto.imagenRes
                        )
                        // ← Mostrar snackbar desde un callback NO composable
                        scope.launch {
                            snackbarHostState.showSnackbar("Agregado: ${producto.nombre} x$cantidad")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductoItemRow(
    producto: ClaseProductos,
    onAgregar: (Int) -> Unit
) {
    val cs = MaterialTheme.colorScheme
    var cantidad by remember { mutableStateOf(1) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = cs.surface,
            contentColor = cs.onSurface
        )
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
                Image(
                    painter = painterResource(id = producto.imagenRes),
                    contentDescription = producto.nombre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    color = cs.surface.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 2.dp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Text(
                        producto.nombre,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = cs.surface.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 2.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                ) {
                    val precio = NumberFormat
                        .getNumberInstance(Locale.forLanguageTag("es-CL"))
                        .format(producto.precio)
                    Text(
                        "\$$precio",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { if (cantidad > 1) cantidad-- }, modifier = Modifier.size(50.dp)) {
                        Text("-")
                    }
                    Text("$cantidad", fontSize = 24.sp)
                    Button(onClick = { cantidad++ }, modifier = Modifier.size(50.dp)) {
                        Text("+")
                    }
                }

                Button(
                    onClick = { onAgregar(cantidad) },
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Agregar", fontSize = 20.sp) }
            }
        }
    }
}


@Composable
private fun ProductoItemRow(
    producto: ClaseProductos,
    onAgregar: (Int) -> Unit
) {
    val cs = MaterialTheme.colorScheme
    var cantidad by remember { mutableStateOf(1) }

    Card(
        modifier = Modifier.fillMaxWidth().height(350.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = cs.surface,
            contentColor = cs.onSurface
        )
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
                Image(
                    painter = painterResource(id = producto.imagenRes),
                    contentDescription = producto.nombre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    color = cs.surface.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 2.dp,
                    modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                ) {
                    Text(producto.nombre, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }

                Surface(
                    color = cs.surface.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 2.dp,
                    modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp)
                ) {
                    val precio = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(producto.precio)
                    Text("\$$precio", fontSize = 18.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { if (cantidad > 1) cantidad-- }, modifier = Modifier.size(50.dp)) {
                        Text("-")
                    }
                    Text("$cantidad", fontSize = 24.sp)
                    Button(onClick = { cantidad++ }, modifier = Modifier.size(50.dp)) {
                        Text("+")
                    }
                }

                Button(
                    onClick = { onAgregar(cantidad) },
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Agregar", fontSize = 20.sp) }
            }
        }
    }
}
