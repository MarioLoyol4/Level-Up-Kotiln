package com.example.level_up.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.level_up.R
import com.example.level_up.remote.BackendClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    init {
        fetchProductos()
    }

   fun fetchProductos(){
        viewModelScope.launch {
            try {
                Log.d("API_BACKEND", "Conectando al servidor en puerto 9090")
                val listaReal = BackendClient.api.getProductos()
                _productos.value = listaReal
                Log.d("API_BACKEND", "Exito! Se cargaron ${listaReal.size} productos.")
            } catch (e: Exception){
                Log.e("API_BACKEND","Error de conexion: ${e.message}")
            }
        }
    }
}

