package com.example.level_up.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun launchWhatsAppSupport(context: Context) {
    val numeroSoporte = "56975525335"
    val url="https://wa.me/$numeroSoporte?text=Hola,%20necesito%20soporte%20técnico%20en%20Level-Up%20Gamer."
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            setPackage("com.whatsapp")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(webIntent)
    }
}

fun launchShareIntent(context: Context) {
    try {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Echale un vistazo a Level-Up Gamer, la mejor tienda gamer en todo chile")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Compartir Level-Up Gamer")
        context.startActivity(shareIntent)
    } catch (e: Exception) {
        Toast.makeText(context, "No se pudo compartir la aplicación", Toast.LENGTH_SHORT).show()
    }
}