package com.example.projetoandroid04_05.core.network

import com.example.projetoandroid04_05.core.model.LoginRequest
import com.example.projetoandroid04_05.core.model.LoginResponse
import com.example.projetoandroid04_05.core.model.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("users/me")
    suspend fun getProfile(): UserProfile

}