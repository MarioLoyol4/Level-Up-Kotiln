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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.level_up.navigation.AppRoute
import com.example.level_up.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(
    onNavigateToHome: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val viewModel: RegistroViewModel = viewModel()
    val estado by viewModel.estado.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())// sirve para darle para abajo (no se que hize que el boton de registrarse no se veia xd)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            TextButton(onClick = onBackToLogin) {
                Text("← Volver al Login")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            "Crear Cuenta",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = estado.nombre,
                onValueChange = viewModel::onNombreChange,
                label = { Text("Nombre (ej. Pedro Fuentes)") },
                isError = estado.errores.nombre != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    estado.errores.nombre?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }
            )
            OutlinedTextField(
                value = estado.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Email (ej. @duocuc.cl)") },
                isError = estado.errores.email != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    estado.errores.email?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                value = estado.contrasena,
                onValueChange = viewModel::onContrasenaChange,
                label = { Text("Contraseña") },
                isError = estado.errores.contrasena != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    estado.errores.contrasena?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                value = estado.fechaNacimiento,
                onValueChange = viewModel::onFechaNacimientoChange,
                label = { Text("Fecha de Nacimiento (AAAA-MM-DD)") },
                //se me hacer re incomodo poner asi la fecha, cambien esta madre luego
                isError = estado.errores.fechaNacimiento != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    estado.errores.fechaNacimiento?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                value = estado.direccion,
                onValueChange = viewModel::onDireccionChange,
                label = { Text("direccion") },
                isError = estado.errores.direccion != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    estado.errores.direccion?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = estado.aceptaTerminos,
                    onCheckedChange = viewModel::onAceptarTerminosChange
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Acepto los términos y condiciones",
                    //deberiamos poner terminos y condiciones?
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (viewModel.estaValidadoElFormulario() && estado.aceptaTerminos) {
                        onNavigateToHome()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                enabled = estado.aceptaTerminos
            ) {
                Text(
                    "Registrarse",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if (!estado.aceptaTerminos) {
                Text(
                    "se deben aceptar los terminos y condiciones para poder acceder",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

    }
}