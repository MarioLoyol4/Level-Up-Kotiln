package com.example.level_up.model

data class IndicadoresResponse(
    val version: String,
    val autor: String,
    val fecha: String,
    val dolar: Indicador
)

data class Indicador(
    val codigo: String,
    val nombre: String,
    val unidadMedia: String,
    val fecha: String,
    val valor: Double
)