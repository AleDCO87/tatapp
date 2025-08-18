package com.example.tatapp.ui.screens.formRegistro

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class FormRegistroViewModel : ViewModel() {
    val nombre = mutableStateOf("")
    val apellido = mutableStateOf("")
    val rut = mutableStateOf("")
    val correo = mutableStateOf("")
    val telefono = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmarPassword = mutableStateOf("")
    val mensaje = mutableStateOf<String?>(null)

    fun validarFormulario() {
        if (nombre.value.isBlank() || apellido.value.isBlank() ||
            rut.value.isBlank() || correo.value.isBlank() ||
            telefono.value.isBlank() || password.value.isBlank() ||
            confirmarPassword.value.isBlank()
        ) {
            mensaje.value = "Todos los campos son obligatorios"
            return
        }

        if (password.value != confirmarPassword.value) {
            mensaje.value = "Las contraseñas no coinciden"
            return
        }

        if (!rut.value.matches(Regex("^[0-9]+-[0-9kK]$"))) {
            mensaje.value = "RUT inválido (ej: 12345678-9)"
            return
        }

        mensaje.value = "Registro completado ✅"
    }
}
