package com.example.level_up.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey val codigo: String,
    val categoria: String,
    val nombre: String,
    val precio: Double
)