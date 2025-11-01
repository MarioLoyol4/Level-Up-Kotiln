package com.example.level_up.viewmodel

import androidx.lifecycle.ViewModel
import com.example.level_up.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.level_up.R

class HomeViewModel : ViewModel() {
    private val _productos = MutableStateFlow(getInitialProducts())
    val productos: StateFlow<List<Producto>> = _productos

    private fun getInitialProducts(): List<Producto>{
        return listOf(
            Producto("CO001",
                "Consolas",
                "PlayStation 5",
                "Consola de última generación",
                549990.0,
                R.drawable.ps5
            ),

            Producto("AC001",
                "Accesorios",
                "Controlador Inalámbrico Xbox Series X",
                "Potencia infinita de gaming",
                59990.0,
                R.drawable.xbox
            ),

            Producto("JM01",
                "Juegos de Mesa",
                "Catan",
                "",
                15990.0,
                R.drawable.catan
            )

        )
    }
    fun getProductsByCategory(category: String): List<Producto> {
        return getInitialProducts().filter {
            it.nombre.contains(category, ignoreCase = true) ||
                    it.descripcion.contains(category, ignoreCase = true)
        }
    }
}

