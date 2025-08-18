package com.example.tatapp.ui.screens.formRegistro

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

// Función calcula digito verificador tarea 1
fun calcularDigitoVerificador(rut: String): String {
    var factor = 2
    var suma = 0
    var i = rut.length - 1
    while (i >= 0) {
        val digito = rut[i].toString().toInt()
        suma += digito * factor
        factor = if (factor == 7) 2 else factor + 1
        i--
    }
    val resto = 11 - (suma % 11)
    return when (resto) {
        11 -> "0"
        10 -> "K"
        else -> resto.toString()
    }
}

//Función para normalizar input, retorna (numero,dv) o null en caso contrario
fun parseRut(input: String): Pair<String, String>? {
    if (input.isBlank()) return null
    val clean = input.replace(".", "").replace(" ", "").lowercase()

    val withDash = if ("-" in clean) clean else {
        if (clean.length < 2) return null
        clean.dropLast(1) + "-" + clean.takeLast(1)
    }

    val parts = withDash.split("-")
    if (parts.size != 2) return null

    val numero = parts[0]
    val dv = parts[1]
    if (numero.isEmpty() || !numero.all { it.isDigit() }) return null
    if (!dv.matches(Regex("^[0-9k]$"))) return null

    return numero to dv
}

//Función que valida rut completo con el calculo
fun esRutValidoConFuncion(input: String): Boolean {
    val parsed = parseRut(input) ?: return false
    val (numero, dvIngresado) = parsed
    val dvEsperado = calcularDigitoVerificador(numero).lowercase()
    return dvIngresado.lowercase() == dvEsperado
}

//Función que formatea Rut a 12.2345.678-5
fun formatearRUT(input: String): String {
    val parsed = parseRut(input) ?: return input
    val (numero,dv) = parsed
    val conPuntos = numero.reversed().chunked(3).joinToString(".").reversed()
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

        if (!esRutValidoConFuncion(rut.value)){
            errorRut.value = "El rut es inválido"
            valido = false
        } else{
            rut.value = formatearRUT(rut.value)
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
