package com.example.tatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tatapp.ui.screens.carrito.CarritoScreen
import com.example.tatapp.ui.screens.carrito.CarritoViewModel
import com.example.tatapp.ui.screens.formRegistro.FormRegistro
import com.example.tatapp.ui.screens.home.Home
import com.example.tatapp.ui.screens.homeProductos.HomeProductosScreen
import com.example.tatapp.ui.screens.productos.ProductosScreen
import com.example.tatapp.ui.screens.productos.ProductosViewModel
import com.example.tatapp.ui.screens.productos.ProductosViewModelFactory
import com.example.tatapp.ui.screens.subcategorias.SubCategoriasScreen
import com.example.tatapp.ui.theme.TatappTheme

class MainActivity : ComponentActivity() {

    private val carritoViewModel: CarritoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TatappTheme {
                //toda la navegación
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "homeProductosScreen") {

                    composable("homeProductosScreen") {
                        HomeProductosScreen(navController = navController)
                    }

                    composable(
                        "subcategorias/{categoria}",
                        arguments = listOf(navArgument("categoria") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoria = backStackEntry.arguments?.getString("categoria") ?: ""

                        SubCategoriasScreen(
                            navController = navController,
                            categoria = categoria
                        )
                    }

                    composable(
                        "productos/{categoria}/{subcategoria}",
                        arguments = listOf(
                            navArgument("categoria") { type = NavType.StringType },
                            navArgument("subcategoria") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                        val subcategoria = backStackEntry.arguments?.getString("subcategoria") ?: ""

                        val viewModel: ProductosViewModel = viewModel(
                            factory = ProductosViewModelFactory(carritoViewModel, categoria, subcategoria)
                        )

                        ProductosScreen(
                            navController = navController,
                            viewModel = viewModel,
                            categoria = categoria,
                            subcategoria = subcategoria
                        )
                    }

                    composable("carrito") {
                        CarritoScreen(navController = navController, viewModel = carritoViewModel)
                    }


                    composable("home") {
                        Home(navController)
                    }

                    composable("registro") {
                        FormRegistro(navController)
                    }


                }
            }
        }
    }
}