package com.chollan.kanapa.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Result: Screen("result")
    object Profile: Screen("profile")
    object Nearest: Screen("nearest")
    object Splash: Screen("splash")
    object Login: Screen("login")
    object Register: Screen("register")
    object About: Screen("about")
}
