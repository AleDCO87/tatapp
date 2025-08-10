package com.example.tatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tatapp.ui.screens.carrito.Carrito
import com.example.tatapp.ui.screens.formRegistro.FormRegistro
import com.example.tatapp.ui.screens.home.Home
import com.example.tatapp.ui.theme.TatappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TatappTheme {
                val navController = rememberNavController()
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "registro"
                ) {
                    composable("registro") {
                        FormRegistro(navController)
                    }

                    composable("home") {
                        Home(
                            //name = "Android",
                            //modifier = Modifier.padding(paddingValues),
                            navController = navController
                        )
                    }

                    composable("carrito") {
                        Carrito(navController)
                    }


                }



            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TatappTheme {

    }
}