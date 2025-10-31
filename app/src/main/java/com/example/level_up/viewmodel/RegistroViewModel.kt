package com.example.level_up.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.level_up.model.RegistroErrores
import com.example.level_up.model.RegistroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeParseException

class RegistroViewModel : ViewModel(){
    private val _estado = MutableStateFlow(RegistroUiState())
    val estado : StateFlow<RegistroUiState> = _estado

    fun onEmailChange(nuevoEmail: String){
        _estado.update { it.copy(email = nuevoEmail, errores = it.errores.copy(email = null)) }
    }

    fun onFechaNacimientoChange(nuevaFecha: String){
        _estado.update { it.copy(fechaNacimiento = nuevaFecha, errores = it.errores.copy(fechaNacimiento = null)) }
    }

    fun onAceptarTerminosChange(aceptado: Boolean){
        _estado.update { it.copy(aceptaTerminos = aceptado) }
    }

    fun onNombreChange(nuevoNombre: String){
        _estado.update { it.copy(nombre = nuevoNombre, errores = it.errores.copy(nombre = null)) }
    }

    fun onContrasenaChange(nuevaContrasena: String){
        _estado.update { it.copy(contrasena = nuevaContrasena, errores = it.errores.copy(contrasena = null)) }
    }

    fun onDireccionChange(nuevaDireccion: String){
        _estado.update { it.copy(direccion = nuevaDireccion, errores = it.errores.copy(direccion = null)) }
    }

    fun estaValidadoElFormulario(): Boolean{
        val formularioActual = _estado.value

        val emailError = if (!Patterns.EMAIL_ADDRESS.matcher(formularioActual.email).matches()) "El correo debe ser valido" else null
        val fechaError = validarEdad(formularioActual.fechaNacimiento)
        val contrasenaError = if (formularioActual.contrasena.length < 6)"La contrasena debe tenerl al menos 6 caracteres" else null
        val nombreError = if (formularioActual.nombre.isBlank()) "El campo es obligatorio" else null
        val direccionError = if (formularioActual.direccion.isBlank()) "El campo es obligatorio" else null


        val errores = RegistroErrores(
            email = emailError,
            fechaNacimiento = fechaError,
            contrasena = contrasenaError,
            nombre = nombreError,
            direccion = direccionError
        )

        val hayErrores = listOfNotNull(errores.email, errores.fechaNacimiento).isNotEmpty()
        _estado.update { it.copy(errores = errores) }
        return !hayErrores
    }

    private fun validarEdad(fechaStr: String): String? {
        return try {
            val fechaNacimiento = LocalDate.parse(fechaStr)
            val hoy = LocalDate.now()
            val edadMinima = hoy.minusYears(18)
            if (fechaNacimiento.isBefore(edadMinima)) null else "Debes ser mayor de 18 anios"
        } catch (e: DateTimeParseException){
            "Formato invalido (AAAA-MM-DD)"
        }
    }
}