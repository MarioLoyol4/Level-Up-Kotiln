package com.example.level_up.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.data.SessionManager
import com.example.level_up.model.LoginErrores
import com.example.level_up.model.LoginRequest
import com.example.level_up.model.LoginUiState
import com.example.level_up.remote.BackendClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application){
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    private val sessionManager = SessionManager(application)

    fun onEmailChange(nuevoEmail: String){
        _estado.update { it.copy(email = nuevoEmail, errores= LoginErrores()) }
    }

    fun onContrasenaChange(nuevaContrasena: String){
        _estado.update { it.copy(contrasena = nuevaContrasena, errores = LoginErrores()) }
    }

    fun performLogin(onSucces: () -> Unit){
        val email = _estado.value.email
        val contrasena = _estado.value.contrasena

        if (email.isBlank() || contrasena.isBlank()){
            _estado.update { it.copy(errores = LoginErrores(general = "Los campos no pueden estar vacios")) }
            return
        }
        viewModelScope.launch {
            try {
                Log.d("LOGIN_API", "Enviando login para: $email")

                val request = LoginRequest(email = email, password = contrasena)
                val response = BackendClient.api.login(request)

                Log.d("LOGIN_API", "Login exitoso. Token: ${response.token}")
                sessionManager.saveSession(response.token, response.email ?: email)
                onSucces()
            } catch (e: Exception){
                Log.e("LOGIN_API" ,"Error: ${e.message}")
                _estado.update{
                    it.copy(errores = LoginErrores(general = "Error de conexion o credenciales invalidas"))

                }
            }
        }
    }
}