package com.example.level_up.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.level_up.model.Producto

class CarritoViewModel {

    var cartItems by mutableStateOf<List<Producto>>(emptyList())
        private set

    fun addToCart(producto: Producto) {
        cartItems = cartItems + producto
    }

    fun removeFromCart(producto: Producto) {
        cartItems = cartItems - producto
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.precio }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun isCartEmpty(): Boolean {
        return cartItems.isEmpty()
    }

    fun getItemCount(): Int {
        return cartItems.size
    }

}