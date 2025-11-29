package com.example.level_up.remote

import com.example.level_up.model.AuthResponse
import com.example.level_up.model.LoginRequest
import com.example.level_up.model.Producto
import com.example.level_up.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BackendApi {
    @GET("/api/productos")
    suspend fun getProductos(): List<Producto>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : AuthResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest) : AuthResponse
}