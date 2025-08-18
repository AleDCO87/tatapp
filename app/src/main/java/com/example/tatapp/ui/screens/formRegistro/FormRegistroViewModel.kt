package com.example.tatapp.ui.screens.formRegistro

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.tatapp.ui.components.esRutValidoConFuncion
import com.example.tatapp.ui.components.formatearRUT

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
