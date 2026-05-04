package com.example.projetoandroid04_05.core.data

import com.example.projetoandroid04_05.core.model.UserProfile
import com.example.projetoandroid04_05.core.network.ApiService

class UserRepository(
    private val apiService: ApiService
) {
    suspend fun getProfile(): UserProfile{
        return apiService.getProfile()
    }
}