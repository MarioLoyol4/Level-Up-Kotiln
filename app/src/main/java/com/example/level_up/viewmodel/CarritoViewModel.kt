package com.example.level_up.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.level_up.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarritoViewModel : ViewModel(){

    private val _cartItems = MutableStateFlow<List<Producto>>(emptyList())
    val cartItems = _cartItems.asStateFlow()


    fun addToCart(producto: Producto) {
        _cartItems.update { currentList ->
            currentList + producto
        }
    }

    fun removeFromCart(producto: Producto) {
        _cartItems.update { currentList ->
            currentList - producto
        }
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.precio }
    }

    fun clearCart() {
        _cartItems.update { emptyList() }
    }

    fun isCartEmpty(): Boolean {
        return _cartItems.value.isEmpty()
    }

    fun getItemCount(): Int {
        return _cartItems.value.size
    }

}