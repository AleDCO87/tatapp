package com.example.tatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.tatapp.modelo.database.AppDatabase
import com.example.tatapp.ui.screens.carrito.CarritoScreen
import com.example.tatapp.ui.screens.carrito.CarritoViewModel
import com.example.tatapp.ui.screens.carrito.CarritoViewModelFactory
import com.example.tatapp.ui.screens.formRegistro.FormRegistro
import com.example.tatapp.ui.screens.home.Home
import com.example.tatapp.ui.screens.homeProductos.HomeProductosScreen
import com.example.tatapp.ui.screens.productos.ProductosScreen
import com.example.tatapp.ui.screens.subcategorias.SubCategoriasScreen
import com.example.tatapp.ui.theme.TatappTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tatapp.viewmodel.SettingsViewModel
import com.example.tatapp.viewmodel.SettingsViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var carritoViewModel: CarritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_db").build()
        val carritoFactory = CarritoViewModelFactory(db.carritoDao())
        carritoViewModel = ViewModelProvider(this, carritoFactory)[CarritoViewModel::class.java]

        setContent {
            // SettingsViewModel desde Compose (más simple)
            val settingsVm: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(application))

            TatappTheme(darkTheme = settingsVm.darkMode.value, dynamicColor = false) {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "homeProductosScreen") {

                    composable("homeProductosScreen") {
                        HomeProductosScreen(navController = navController, settingsVm = settingsVm)
                    }

                    composable(
                        "subcategorias/{categoria}",
                        arguments = listOf(navArgument("categoria") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                        SubCategoriasScreen(navController = navController, categoria = categoria)
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
                        ProductosScreen(
                            navController = navController,
                            carritoViewModel = carritoViewModel,
                            categoria = categoria,
                            subcategoria = subcategoria
                        )
                    }

                    composable("carrito") {
                        CarritoScreen(navController = navController, viewModel = carritoViewModel)
                    }

                    composable("home") { Home(navController) }
                    composable("registro") { FormRegistro(navController) }
                }
            }
        }
    }
}
