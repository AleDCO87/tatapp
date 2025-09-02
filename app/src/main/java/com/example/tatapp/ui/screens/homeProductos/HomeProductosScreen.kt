package com.example.tatapp.ui.screens.homeProductos

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tatapp.R
import com.example.tatapp.ui.components.TopBarOverflowMenu
import com.example.tatapp.ui.screens.productos.ClaseProductos
import com.example.tatapp.ui.screens.productos.productosBase
import com.example.tatapp.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProductosScreen(
    navController: NavHostController,
    settingsVm: SettingsViewModel
) {
    val cs = MaterialTheme.colorScheme
    val dark by settingsVm.darkMode.collectAsState()
    var selectedItem by remember { mutableStateOf(0) }

    // tus listas
    val todasCategorias = categoriasProductos + categoriasServicios

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(" ", fontSize = 30.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Perfil")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("carrito") }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
                    }
                    TopBarOverflowMenu(
                        isDark = dark,
                        onToggleDark = { settingsVm.toggleDark() },
                        onOpenPerfil = { navController.navigate("home") },
                        onOpenConfig = { /* navController.navigate("config") si tienes esa pantalla */ }
                    )
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 4.dp,
                color = cs.primary
            ) {
                val chipContent = contentColorFor(cs.primary)
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(todasCategorias) { index, categoria ->
                        val isSelected = index == selectedItem
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(
                                    if (isSelected) cs.secondaryContainer.copy(alpha = 0.3f) else cs.primary.copy(alpha = 0f),
                                    RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedItem = index
                                    navController.navigate("subcategorias/${categoria.nombreCat}")
                                }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = categoria.iconoCat),
                                contentDescription = categoria.nombreCat,
                                modifier = Modifier.size(28.dp),
                                tint = chipContent
                            )
                            Text(
                                text = categoria.nombreCat,
                                fontSize = 12.sp,
                                color = chipContent,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(cs.background)
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Text(
                    text = "¿Qué necesitas hoy?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = cs.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            items(items = todasCategorias, key = { it.nombreCat }) { categoria ->
                val productosCat = remember(categoria.nombreCat) {
                    productosBase.filter {
                        it.categoria.name == categoria.nombreCat || it.categoria.displayName == categoria.nombreCat
                    }
                }
                if (productosCat.isNotEmpty()) {
                    CategoriaSection(
                        nombreCategoria = categoria.nombreCat,
                        productos = productosCat,
                        onVerTodoClick = { navController.navigate("subcategorias/${categoria.nombreCat}") },
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriaSection(
    nombreCategoria: String,
    productos: List<ClaseProductos>,
    onVerTodoClick: () -> Unit,
    navController: NavHostController
) {
    val cs = MaterialTheme.colorScheme

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nombreCategoria,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = cs.onBackground
            )
            Button(
                onClick = onVerTodoClick,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) { Text(text = "Ver todo", fontSize = 18.sp) }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = productos, key = { it.id }) { producto ->
                ProductoCard(nombre = producto.nombre) {
                    val catEnumName = producto.categoria.name
                    val sub = Uri.encode(producto.subcategoria)
                    navController.navigate("productos/$catEnumName/$sub")
                }
            }
        }
    }
}

@Composable
private fun ProductoCard(nombre: String, onClick: () -> Unit) {
    val cs = MaterialTheme.colorScheme
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(width = 160.dp, height = 200.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = cs.surface,
            contentColor = cs.onSurface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ej_alim),
                contentDescription = nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(140.dp).fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = nombre, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}
