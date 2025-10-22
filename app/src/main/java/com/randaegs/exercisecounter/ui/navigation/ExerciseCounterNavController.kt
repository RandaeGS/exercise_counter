package com.randaegs.exercisecounter.ui.navigation

object Destinations {
    const val HOME = "home"
    const val SETTINGS = "settings"
}

sealed class NavigationEvent {
    object GoBack : NavigationEvent()
    object ToHome: NavigationEvent()
    object ToSettings: NavigationEvent()
}
