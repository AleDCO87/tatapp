package com.example.tatapp.ui.screens.productos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(navController: NavHostController, viewModel: ProductosViewModel) {

    var expanded by remember { mutableStateOf(false) }  //del dropdown


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Productos", fontSize = 30.sp) },
                /*
                navigationIcon = {
                    IconButton(onClick = { /* Abrir menú */ }) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menú")
                    }
                },*/
                /*
                actions = {
                    IconButton(onClick = { /* Ir al perfil */ }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
                    }
                }
                */    //para despues
            )
        },
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    icon = { Icon(Icons.Rounded.Menu, contentDescription = "Productos") },
                    label = { Text("Productos", fontSize = 18.sp) },
                    selected = true,
                    onClick = { /* Ya estás aquí */ }
                )

                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (viewModel.carritoViewModel.totalEnCarrito > 0) {
                                    Badge { Text(viewModel.carritoViewModel.totalEnCarrito.toString()) }
                                }
                            }
                        ) {
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
        Column(modifier = Modifier.padding(innerPadding).padding(8.dp)) {

            // categorias de productos
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = viewModel.selectedCategoriaText,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Filtrar por categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Todo") },
                        onClick = {
                            viewModel.seleccionarCategoria(null)
                            expanded = false
                        }
                    )
                    CategoriaProducto.entries.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat.displayName) },
                            onClick = {
                                viewModel.seleccionarCategoria(cat)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.productosFiltrados) { producto ->
                    ProductoItemRow(producto, viewModel)
                }
            }
        }
    }
}

@Composable
fun ProductoItemRow(producto: ClaseProductos, viewModel: ProductosViewModel) {
    var cantidad by remember { mutableStateOf(1) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
        border = (BorderStroke(2.dp, Color.Gray))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Image(
                    painter = painterResource(id = producto.imagenRes),
                    contentDescription = producto.nombre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Texto en esquina superior izquierda con fondo semitransparente
                Text(
                    text = producto.nombre,
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                        .background(color = Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )

                // Texto en esquina inferior derecha con fondo semitransparente
                Text(
                    text = "\$${NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL")).format(producto.precio)}",
                    fontSize = 27.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                        .background(color = Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (cantidad > 1) cantidad-- },
                        modifier =Modifier.size(50.dp)
                    ) { Text("-", textAlign = TextAlign.Center, fontSize = 20.sp) }
                    Text(
                        "$cantidad", fontSize = 30.sp)
                    Button(
                        onClick = { cantidad++ },
                        modifier =Modifier.size(50.dp)
                    ) { Text("+", textAlign = TextAlign.Center, fontSize = 20.sp) }
                }

                Button(
                    onClick = { viewModel.agregarAlCarrito(producto, cantidad) },
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Agregar", fontSize = 24.sp)
                }



            }

        }




    }
}