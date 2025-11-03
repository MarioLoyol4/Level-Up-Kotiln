package com.example.level_up.model

data class LoginErrores(
    val email: String? = null,
    val contrasena: String? = null,
    val general: String? = null
)

data class LoginUiState(
    val email: String ="",
    val contrasena: String = "",
    val errores: LoginErrores = LoginErrores()
)