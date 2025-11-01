package com.example.level_up.model

import android.graphics.Bitmap

data class Producto(
    val codigo: String,
    val categoria: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: Int
)
