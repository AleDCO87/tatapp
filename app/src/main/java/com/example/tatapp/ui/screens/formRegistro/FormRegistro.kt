package com.example.tatapp.ui.screens.formRegistro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tatapp.ui.components.esRutValidoConFuncion
import com.example.tatapp.ui.components.formatearRUT
import kotlinx.coroutines.delay
import com.example.tatapp.ui.components.rutCompleto

@Composable
fun FormRegistro(
    navController: NavController,
    vm: FormRegistroViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Mensajes de advertencia de campos generales
    LaunchedEffect(vm.mensaje.value) {
        vm.mensaje.value?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            vm.mensaje.value = null
        }
    }

    // Confirmación y navegación de registro exitoso
    LaunchedEffect(vm.registroExitoso.value) {
        if (vm.registroExitoso.value) {
            snackbarHostState.showSnackbar("Registro Completado ✅")
            delay(1000)
            navController.navigate("categorias"){
                popUpTo("registro"){
                    inclusive = true
                }
                vm.registroExitoso.value = false
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(Modifier.height(16.dp))

            // Nombre
            OutlinedTextField(
                value = vm.nombre.value,
                onValueChange = { vm.nombre.value = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorNombre.value != null) {
                Text(
                    text = vm.errorNombre.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            // Apellido
            OutlinedTextField(
                value = vm.apellido.value,
                onValueChange = { vm.apellido.value = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorApellido.value != null) {
                Text(
                    text = vm.errorApellido.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            // RUT
            OutlinedTextField(
                value = vm.rut.value,
                onValueChange = { input ->
                    vm.rut.value = input
                    if (rutCompleto(input)){
                        if (esRutValidoConFuncion(input)){
                            vm.errorRut.value = null
                            vm.rut.value = formatearRUT(input)
                        } else {
                            vm.errorRut.value = "El rut es inválido"
                        }
                    } else {
                        vm.errorRut.value = null
                    } },
                label = { Text("RUT (12.345.678-9)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = vm.errorRut.value != null,
                supportingText = {
                    vm.errorRut.value?.let{ msg ->
                        Text(text = msg, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            // Correo
            OutlinedTextField(
                value = vm.correo.value,
                onValueChange = { vm.correo.value = it },
                label = { Text("ejemplo@dominio.com") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorCorreo.value != null) {
                Text(
                    text = vm.errorCorreo.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            // Teléfono
            OutlinedTextField(
                value = vm.telefono.value,
                onValueChange = { vm.telefono.value = it },
                label = { Text("+56 9 1234 5678") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorTelefono.value != null) {
                Text(
                    text = vm.errorTelefono.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            // Contraseña
            OutlinedTextField(
                value = vm.password.value,
                onValueChange = { vm.password.value = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorPassword.value != null) {
                Text(
                    text = vm.errorPassword.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            // Confirmar contraseña
            OutlinedTextField(
                value = vm.confirmarPassword.value,
                onValueChange = { vm.confirmarPassword.value = it },
                label = { Text("Confirmar contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            if (vm.errorConfirmPassword.value != null) {
                Text(
                    text = vm.errorConfirmPassword.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(Modifier.height(16.dp))

            // Botón Registrar
            Button(
                onClick = {
                    vm.validarFormulario()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { navController.popBackStack() }) {
                Text("Cancelar")
            }
        }
    }
}
