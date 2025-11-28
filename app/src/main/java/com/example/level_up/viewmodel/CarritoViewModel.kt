package com.example.level_up.viewmodel
import androidx.lifecycle.ViewModel
import com.example.level_up.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarritoViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Producto>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    init {
        calculateTotalPrice()
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
}