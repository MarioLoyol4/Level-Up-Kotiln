package com.example.level_up.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.level_up.components.ProductCard
import com.example.level_up.model.Producto
import com.example.level_up.model.launchShareIntent
import com.example.level_up.model.launchWhatsAppSupport
import com.example.level_up.navigation.AppRoute
import com.example.level_up.viewmodel.CarritoViewModel
import com.example.level_up.viewmodel.HomeViewModel
import com.example.level_up.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    homeViewModel: HomeViewModel,
    carritoViewModel: CarritoViewModel
) {

    val productos by homeViewModel.productos.collectAsState()
    val context = LocalContext.current
    val cartItems by carritoViewModel.cartItems.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchProductos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Level-Up Gamer") },
                actions = {

                    IconButton(onClick = {
                        mainViewModel.navigateTo(AppRoute.Carrito)
                    }) {
                        BadgedBox(
                            badge = {
                                if (cartItems.isNotEmpty()) {
                                    Badge {
                                        Text(cartItems.size.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.ShoppingCart, "Carrito")
                        }
                    }

                    IconButton(onClick = { launchShareIntent(context) }) {
                        Icon(Icons.Default.Share, "Compartir la aplicacion")
                    }

                    IconButton(onClick = {
                        mainViewModel.navigateTo(
                            AppRoute.Login,
                            popUpRoute = AppRoute.Home,
                            inclusive = true
                        )
                    }) {
                        Icon(Icons.Default.Close, "Cerrar Sesión")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { launchWhatsAppSupport(context) }) {
                Icon(Icons.Default.Phone, "Soporte via whatsapp")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(productos) { producto ->
                ProductCard(
                    producto = producto,
                    onAddToCart = {
                        carritoViewModel.addToCart(producto)
                        Toast.makeText(context, " ${producto.nombre} añadido correctamente", Toast.LENGTH_SHORT).show()
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}