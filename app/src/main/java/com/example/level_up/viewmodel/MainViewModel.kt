package com.example.level_up.viewmodel

import androidx.lifecycle.ViewModel
import com.example.level_up.navigation.AppRoute
import com.example.level_up.navigation.NavigationEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private val _navEvents = MutableSharedFlow<NavigationEvent>()
    val navEvents = _navEvents.asSharedFlow()

    fun navigateTo(appRoute: AppRoute){
        CoroutineScope(Dispatchers.Main).launch {
            _navEvents.emit(NavigationEvent.NavigateTo(appRoute))
        }
    }
    fun navigateTo(
        appRoute: AppRoute,
        popUpRoute: AppRoute? = null,
        inclusive: Boolean = false,
        singleTop: Boolean = false
    ){
        CoroutineScope(Dispatchers.Main).launch {
            _navEvents.emit(
                NavigationEvent.NavigateTo(
                    appRoute = appRoute,
                    popUpRoute = popUpRoute,
                    inclusive = inclusive,
                    singleTop = singleTop
                )
            )
        }
    }

    fun navigateBack(){
        CoroutineScope(Dispatchers.Main).launch {
            _navEvents.emit(NavigationEvent.PopBackStack)
        }
    }
}