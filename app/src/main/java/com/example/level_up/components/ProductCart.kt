package com.example.level_up.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.level_up.model.Producto

@Composable
fun ProductCard(
    producto: Producto,
    onAddToCart: () -> Unit
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = producto.imagen),
            contentDescription = producto.nombre,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = producto.nombre,
            color = Color(0xFF39FF14),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = producto.descripcion,
            color = Color(0xFFD3D3D3),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$${producto.precio.toInt()}",
                color = Color(0xFF1E90FF),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onAddToCart,
                modifier = Modifier.width(100.dp)
            ) {
                Text("añadir")
            }
        }
    }
}