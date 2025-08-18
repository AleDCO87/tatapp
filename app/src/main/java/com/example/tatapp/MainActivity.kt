package com.example.tatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tatapp.ui.screens.carrito.CarritoScreen
import com.example.tatapp.ui.screens.carrito.CarritoViewModel
import com.example.tatapp.ui.screens.categorias.CategoriasScreen
import com.example.tatapp.ui.screens.formRegistro.FormRegistro
import com.example.tatapp.ui.screens.home.Home
import com.example.tatapp.ui.screens.productos.ProductosScreen
import com.example.tatapp.ui.screens.productos.ProductosViewModel
import com.example.tatapp.ui.screens.productos.ProductosViewModelFactory
import com.example.tatapp.ui.theme.TatappTheme

class MainActivity : ComponentActivity() {

    private val carritoViewModel: CarritoViewModel by viewModels()
    private val productosViewModel: ProductosViewModel by viewModels {
        ProductosViewModelFactory(carritoViewModel)    //esto porque productos usa la lista global mutable del carrito
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TatappTheme {

                //toda la navegación
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {

                    composable("registro") {
                        FormRegistro(navController)
                    }

                    composable("home") {
                        Home(navController = navController)
                    }

                    composable("categorias") {
                        CategoriasScreen(navController = navController)
                    }

                    composable("productos") {
                            backStackEntry ->
                        ProductosScreen(navController = navController, viewModel = productosViewModel)
                    }

                    composable("carrito") {
                        CarritoScreen(navController = navController, viewModel = carritoViewModel)
                    }
                }
            }
        }
    }
}