package com.example.level_up.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val email: String? = null,
    val message: String? = null
)