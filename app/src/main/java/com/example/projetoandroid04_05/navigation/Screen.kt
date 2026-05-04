package com.example.projetoandroid04_05.navigation

sealed class Screen(
    val route:String
){
    data object Login: Screen("login")
    data object Home: Screen("home")
    data object Profile: Screen("profile")
}