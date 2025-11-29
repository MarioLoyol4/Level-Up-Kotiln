package com.example.level_up.remote

import com.example.level_up.model.IndicadoresResponse
import retrofit2.http.GET

interface IndicadoresApi {
    @GET("api")
    suspend fun obtenerIndicadores(): IndicadoresResponse
}