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

            Producto("JM01",
                "Juegos de Mesa",
                "Catan",
                "Un clásico juego de estrategia donde los jugadores compiten por colonizar y expandirse en la isla de Catan. Ideal para 3-4 jugadores y perfecto para noches de juego en familia o con amigos",
                15990.0,
                R.drawable.catan
            ),
            Producto(
                "JM002",
                "Juegos de mesa",
                "Carcassonne",
                "Un juego de colocación de fichas donde los jugadores construyen el paisaje alrededor de la fortaleza medieval de Carcassonne. Ideal para 2-5 jugadores y fácil de aprender.",
                24990.0,
                R.drawable.carcassonne
            ),
            Producto("AC001",
                "Accesorios",
                "Controlador Inalámbrico Xbox Series X",
                "Ofrece una experiencia de juego cómoda con botones mapeables y una respuesta táctil mejorada. Compatible con consolas Xbox y PC. ",
                59990.0,
                R.drawable.xbox
            ),
            Producto("AC002",
                "Accesorios",
                "Auriculares Gamer HyperX Cloud II",
                " Proporcionan un sonido envolvente de calidad con un micrófono desmontable y almohadillas de espuma viscoelástica para mayor comodidad durante largas sesiones de juego.",
                79990.0,
                R.drawable.hyperxcloud
            ),
            Producto("CO001",
                "Consolas",
                "PlayStation 5",
                "La consola de última generación de Sony, que ofrece gráficos impresionantes y tiempos de carga ultrarrápidos para una experiencia de juego inmersiva.",
                549990.0,
                R.drawable.ps5
            ),
            Producto("CG001",
                "Computadores Gamers",
                "PC Gamer ASUS ROG Strix ",
                "Un potente equipo diseñado para los gamers más exigentes, equipado con los últimos componentes para ofrecer un rendimiento excepcional en cualquier juego.",
                1299990.0,
                R.drawable.asusrog
            ),
            Producto("SG001",
                "Sillas Gamers",
                "Silla Gamer Secretlab Titan",
                "Diseñada para el máximo confort, esta silla ofrece un soporte ergonómico y personalización ajustable para sesiones de juego prolongadas. ",
                349990.0,
                R.drawable.secretlabtitan
            ),
            Producto("MS001",
                "Mouse",
                "Mouse Gamer Logitech G502 HERO ",
                "Con sensor de alta precisión y botones personalizables, este mouse es ideal para gamers que buscan un control preciso y personalización.",
                49990.0,
                R.drawable.mouselogitech
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

