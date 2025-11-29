package com.example.level_up.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.model.Producto
import com.example.level_up.remote.IndicadoresClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Producto>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    private val _valorDolar = MutableStateFlow<Double?>(null)
    val valorDolar = _valorDolar.asStateFlow()

    init {
        calculateTotalPrice()
        obtenerValorDolar()
    }

    fun addToCart(producto: Producto) {
        _cartItems.update { currentList ->
            val newList = currentList + producto
            newList
        }
        calculateTotalPrice()
    }

    fun removeFromCart(producto: Producto) {
        _cartItems.update { currentList ->
            val newList = currentList.toMutableList().apply {
                remove(producto)
            }
            newList
        }
        calculateTotalPrice()
    }

    fun clearCart() {
        _cartItems.update { emptyList() }
        calculateTotalPrice()
    }

    fun isCartEmpty(): Boolean {
        return _cartItems.value.isEmpty()
    }

    fun getItemCount(): Int {
        return _cartItems.value.size
    }

    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value.sumOf { it.precio }
    }

    fun getTotalPriceCLP(): Double{
        return _totalPrice.value
    }

    fun getTotalPriceUSD(): Double{
        val dolar = _valorDolar.value
        val totalClp = getTotalPriceCLP()

        return if (dolar != null && dolar > 0){
            totalClp / dolar
        } else {
            0.0
        }
    }

    private fun obtenerValorDolar(){
        viewModelScope.launch {
            try {
                val response = IndicadoresClient.instance.obtenerIndicadores()
                _valorDolar.value = response.dolar.valor
                Log.d("API_DOLAR", "Valor del dolar obtenido: ${response.dolar.valor}")
            } catch (e: Exception) {
                Log.e("API_DOLAR", "Error obteniendo dolar: ${e.message}")
                _valorDolar.value = null
            }
        }
    }
}