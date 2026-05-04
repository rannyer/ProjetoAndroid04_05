package com.example.projetoandroid04_05.core.data

import com.example.projetoandroid04_05.core.model.LoginRequest
import com.example.projetoandroid04_05.core.network.ApiService
import com.example.projetoandroid04_05.core.session.TokenManager

class AuthRepository(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {
    suspend fun login(email:String, password:String){
        val response = apiService.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        tokenManager.saveToken(response.token)
    }

    suspend fun  logout(){
        tokenManager.clearToken()


    }
}