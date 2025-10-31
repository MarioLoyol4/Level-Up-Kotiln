package com.example.level_up.navigation

sealed class AppRoute(val route: String){
    data object Registro : AppRoute("registro")
    data object Home : AppRoute("home")
}