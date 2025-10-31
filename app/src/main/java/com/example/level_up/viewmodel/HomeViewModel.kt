package com.example.level_up.viewmodel

import androidx.lifecycle.ViewModel
import com.example.level_up.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _productos = MutableStateFlow(getInitialProducts())
    val productos: StateFlow<List<Producto>> = _productos

    private fun getInitialProducts(): List<Producto>{
        return listOf(
            Producto("JM01","Juegos de Mesa", "Catan", 15990.0),
            Producto("CO001", "Consolas", "PlayStation 5", 549990.0),
            Producto("SG001", "Sillas Gamers", "Silla Gamer Secretlab Titan", 349990.0),
            Producto("AC001", "Accesorios", "Controlador Inalámbrico Xbox Series X", 59990.0),
            Producto("PP001", "Poleras Personalizadas", "Polera Gamer Personalizada 'Level-Up'", 14990.0),
            Producto("JM002", "Juegos de Mesa", "Carcassonne", 24990.0),
            Producto("AC002", "Accesorios", "Auriculares Gamer HyperX Cloud II", 79990.0),
            Producto("CG001", "Computadores Gamers", "PC Gamer ASUS ROG Strix", 1299990.0),
            Producto("MS001", "Mouse", "Mouse Gamer Logitech G502 HERO", 49990.0),
            Producto("MP001", "Mousepad", "Mousepad Razer Goliathus Extended Chroma", 29990.0)
        )
    }
}

