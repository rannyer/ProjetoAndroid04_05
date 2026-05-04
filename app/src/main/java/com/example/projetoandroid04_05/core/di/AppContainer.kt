package com.example.projetoandroid04_05.core.di

import android.content.Context
import com.example.projetoandroid04_05.core.data.AuthRepository
import com.example.projetoandroid04_05.core.data.UserRepository
import com.example.projetoandroid04_05.core.network.NetworkModule
import com.example.projetoandroid04_05.core.session.TokenManager

class AppContainer(context: Context){
    val tokenManager = TokenManager(context)

    private val apiService = NetworkModule.createApiService(tokenManager)

    val authRepository = AuthRepository(
        apiService = apiService,
        tokenManager = tokenManager
    )

    val userRepository = UserRepository(
        apiService
    )
}