package com.example.projetoandroid04_05.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projetoandroid04_05.core.di.AppContainer
import com.example.projetoandroid04_05.feature.auth.LoginScreen
import com.example.projetoandroid04_05.feature.auth.LoginViewModel
import com.example.projetoandroid04_05.feature.home.HomeScreen
import com.example.projetoandroid04_05.feature.profile.ProfileScreen
import com.example.projetoandroid04_05.feature.profile.ProfileViewModel

@Composable
fun AppNavigation(appContainer: AppContainer) {
    val navController = rememberNavController()

    val token by appContainer.tokenManager.token.collectAsStateWithLifecycle(initialValue = null)

    val startDestination = if (token.isNullOrBlank()){
        Screen.Login.route
    }else{
        Screen.Home.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route){
            val viewModel = remember {
                LoginViewModel(appContainer.authRepository)
            }
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(Screen.Login.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable (Screen.Profile.route){
            val viewModel = remember {
                ProfileViewModel(
                    userRepository = appContainer.userRepository,
                    authRepository = appContainer.authRepository,
                )
            }
            ProfileScreen(
                viewModel = viewModel,
                onLogout = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(0)
                    }
                }
            )
        }



    }











}