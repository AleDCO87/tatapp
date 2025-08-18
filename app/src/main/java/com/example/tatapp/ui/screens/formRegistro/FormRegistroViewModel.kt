package com.example.tatapp.ui.screens.formRegistro

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

//Función que formatea Rut a 12.2345.678-5
fun formatearRUT(input: String): String {
    val rutLimpio = input.replace(".","").replace(" ","").lowercase()
    if (rutLimpio.length < 2) return input
    val cuerpo = rutLimpio.dropLast(1)
    val dv = rutLimpio.last()
    val conPuntos = cuerpo.reversed().chunked(3).joinToString(".").reversed()
    return "$conPuntos-$dv".uppercase()
}
class FormRegistroViewModel : ViewModel() {
    // ----> Campos del formulario <----
    val nombre = mutableStateOf("")
    val apellido = mutableStateOf("")
    val rut = mutableStateOf("")
    val correo = mutableStateOf("")
    val telefono = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmarPassword = mutableStateOf("")
    val mensaje = mutableStateOf<String?>(null)

    // ----> Errores asociados a los campos <----
    var errorNombre = mutableStateOf<String?>(null)
    var errorApellido = mutableStateOf<String?>(null)
    var errorRut = mutableStateOf<String?>(null)
    var errorCorreo = mutableStateOf<String?>(null)
    var errorTelefono = mutableStateOf<String?>(null)
    var errorPassword = mutableStateOf<String?>(null)
    var errorConfirmPassword = mutableStateOf<String?>(null)

    // ----> Flag de registro exitoso <----
    var registroExitoso = mutableStateOf(false)

    fun validarFormulario() {
        errorNombre.value = null
        errorApellido.value = null
        errorRut.value = null
        errorCorreo.value = null
        errorTelefono.value = null
        errorPassword.value = null
        errorConfirmPassword.value = null

        var valido = true

        if (nombre.value.isBlank()){
            errorNombre.value = "El nombre es obligatorio"
            valido = false
        }

        if (apellido.value.isBlank()){
            errorApellido.value = "El apellido es obligatorio"
            valido = false
        }

        if (!rut.value.matches(Regex("^[0-9]+-[0-9kK]{1}$"))){
            errorRut.value = "El rut es inválido"
            valido = false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo.value).matches()){
            errorCorreo.value = "El Correo es inválido"
            valido = false
        }

        if (telefono.value.length < 8){
            errorTelefono.value = "El telefono es incorrecto"
            valido = false
        }

        if (password.value.length < 6){
            errorPassword.value = "La contraseña debe tener al menos 6 caracteres"
            valido = false
        }

        if (password.value != confirmarPassword.value){
            errorConfirmPassword.value = "Las contraseñas no coinciden"
            valido = false
        }

        if (valido){
            registroExitoso.value = true
        }
    }
}
