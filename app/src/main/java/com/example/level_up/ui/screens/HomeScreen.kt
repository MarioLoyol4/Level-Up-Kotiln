package com.example.level_up.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.level_up.model.Producto
import com.example.level_up.navigation.AppRoute
import com.example.level_up.viewmodel.HomeViewModel
import com.example.level_up.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    homeViewModel: HomeViewModel
){
    val productos by homeViewModel.productos.collectAsState()
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Level-Up Gamer" , style = MaterialTheme.typography.headlineMedium) },
            actions = {
                IconButton(onClick = {
                    launchShareIntent(context)
                }) {
                    Icon(Icons.Default.Share, "Compartir App")
                }
            }
        )
    },
       floatingActionButton = {
           FloatingActionButton(
               onClick = { launchWhatsAppSupport(context)},
               containerColor = MaterialTheme.colorScheme.secondary
           ) {
               Icon(Icons.Default.Phone, "Soporte WhatsApp", tint = MaterialTheme.colorScheme.onSecondary)
           }
       }
        ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productos) {producto ->
                ProductoCard(producto = producto)
            }
        }
    }
}

private fun launchWhatsAppSupport(context: Context){
    val numeroSoporte ="+56912345678"
    val url = "https://wa.me/$numeroSoporte?text=Hola,%20necesito%20soporte%20técnico."
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp")
    } catch (e: Exception){
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(webIntent)
    }
}
private fun launchShareIntent(context: Context){
    try {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Echa un vistazo a Level-Up Gamer")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    } catch (e: Exception){
        Toast.makeText(context, "No se pudo compartir la App", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ProductoCard(producto: Producto){
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface )
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)) {
                Text(producto.nombre, style = MaterialTheme.typography.bodyLarge)
                Text(producto.categoria, style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = "$${producto.precio.toInt()}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}