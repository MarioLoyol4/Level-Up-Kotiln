package com.example.level_up.model

data class RegistroErrores(
    val email: String? = null,
    val fechaNacimiento: String? = null,
    val contrasena: String? = null,
    val nombre: String?= null,
    val direccion: String?=null
)

data class RegistroUiState(
    val email: String = "",
    val fechaNacimiento: String = "",
    val aceptaTerminos: Boolean = false,
    val contrasena: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val errores: RegistroErrores = RegistroErrores()
)
