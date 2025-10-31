package com.example.level_up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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

import com.example.level_up.ui.screens.HomeScreen
import com.example.level_up.ui.screens.RegistroScreen
import com.example.level_up.ui.theme.LevelUpTheme
import com.example.level_up.viewmodel.HomeViewModel
import com.example.level_up.viewmodel.MainViewModel
import com.example.level_up.viewmodel.RegistroViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LevelUpTheme {
                val mainViewModel : MainViewModel = viewModel()
                val registroViewModel : RegistroViewModel = viewModel()
                val homeViewModel : HomeViewModel = viewModel()

                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    mainViewModel.navEvents.collectLatest{ event ->
                        when(event){
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.appRoute.route){
                                    event.popUpRoute?.let {
                                        popUpTo(it.route){ inclusive = event.inclusive}
                                    }
                                    launchSingleTop = event.singleTop
                                }
                            }
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppRoute.Registro.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(AppRoute.Registro.route){
                            RegistroScreen(registroViewModel, navController)
                        }

                        composable(AppRoute.Home.route){
                            HomeScreen(mainViewModel,homeViewModel)
                        }
                    }
                }

                }
            }
        }
    }
