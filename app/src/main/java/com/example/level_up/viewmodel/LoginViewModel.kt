package com.example.level_up.viewmodel

import androidx.lifecycle.ViewModel
import com.example.level_up.model.LoginErrores
import com.example.level_up.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel(){
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    fun onEmailChange(nuevoEmail: String){
        _estado.update { it.copy(email = nuevoEmail, errores= LoginErrores()) }
    }

    fun onContrasenaChange(nuevaContrasena: String){
        _estado.update { it.copy(contrasena = nuevaContrasena, errores = LoginErrores()) }
    }

    fun onLoginClick(): Boolean {
        val email = _estado.value.email
        val contrasena = _estado.value.contrasena

        if (email.isBlank() || contrasena.isBlank()){
            _estado.update { it.copy(errores = LoginErrores(general = "Los campos no pueden estar vacios")) }
            return false
        }
        if (email == "admin@duoc.cl" && contrasena == "123456"){
            _estado.update { it.copy(errores = LoginErrores()) }
            return true
        } else {
            _estado.update { it.copy(errores = LoginErrores(general = "Email o contrasena incorrectos")) }
            return false
        }
    }
}