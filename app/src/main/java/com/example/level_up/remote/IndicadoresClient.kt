package com.example.level_up.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IndicadoresClient{
    private const val BASE_URL = "https://mindicador.cl"

    val instance: IndicadoresApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(IndicadoresApi::class.java)
    }
}