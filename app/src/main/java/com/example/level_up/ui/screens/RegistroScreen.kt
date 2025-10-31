package com.example.level_up.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.navigation.AppRoute
import com.example.level_up.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    navController: NavController
) {
    val estado by viewModel.estado.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = estado.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre (ej. Pedro Fuentes)") },
            isError = estado.errores.nombre != null,
            singleLine = true,
            supportingText = {
                estado.errores.nombre?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.contrasena,
            onValueChange = viewModel::onContrasenaChange,
            label = { Text("Contrasena") },
            isError = estado.errores.contrasena != null,
            singleLine = true,
            supportingText = {
                estado.errores.contrasena?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email (ej. @duocuc.cl)") },
            isError = estado.errores.email != null,
            singleLine = true,
            supportingText = {
                estado.errores.email?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.fechaNacimiento,
            onValueChange = viewModel::onFechaNacimientoChange,
            label = { Text("Fecha Nacimiento (AAAA-MM-DD)") },
            isError = estado.errores.fechaNacimiento != null,
            singleLine = true,
            supportingText = {
                estado.errores.fechaNacimiento?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado.direccion,
            onValueChange = viewModel::onDireccionChange,
            label = { Text("Direccion") },
            isError = estado.errores.direccion != null,
            singleLine = true,
            supportingText = {
                estado.errores.direccion?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = estado.aceptaTerminos,
                onCheckedChange = viewModel::onAceptarTerminosChange
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto los terminos y condiciones")
        }
        Button(
            onClick = {
                if (viewModel.estaValidadoElFormulario() && estado.aceptaTerminos) {
                     navController.navigate(AppRoute.Home.route)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Registrar", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}
