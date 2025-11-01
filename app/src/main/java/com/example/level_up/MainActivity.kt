package com.example.level_up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_up.navigation.AppRoute
import com.example.level_up.navigation.NavigationEvent
import com.example.level_up.ui.screens.CarritoScreen

import com.example.level_up.ui.screens.HomeScreen
import com.example.level_up.ui.screens.LoginScreen
import com.example.level_up.ui.screens.RegistroScreen
import com.example.level_up.ui.theme.LevelUpTheme
import com.example.level_up.viewmodel.CarritoViewModel
import com.example.level_up.viewmodel.HomeViewModel
import com.example.level_up.viewmodel.MainViewModel
import com.example.level_up.viewmodel.RegistroViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val carritoViewModel: CarritoViewModel by viewModels()
    private val registroViewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LevelUpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("home")
                                },
                                onNavigateToRegister = {
                                    navController.navigate("registro")
                                }
                            )
                        }

                        composable("registro") {
                            RegistroScreen(
                                onNavigateToHome = {
                                    navController.navigate("home")
                                },
                                onBackToLogin = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(
                                onNavigateToCart = {
                                    navController.navigate("carrito")
                                },
                                onLogout = {
                                    navController.navigate("login") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable("carrito") {
                            CarritoScreen(
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}