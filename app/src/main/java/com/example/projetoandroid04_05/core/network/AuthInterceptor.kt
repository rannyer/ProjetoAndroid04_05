package com.example.projetoandroid04_05.core.network

import android.util.Log
import com.example.projetoandroid04_05.core.session.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.token.first()
        }
        Log.e("INTERCEPTOR_VM", token.toString())

        val request = if(!token.isNullOrBlank()) {
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }else{
            chain.request()
        }
        Log.e("INTERCEPTOR_VM_CHAIN", "Headers ${request.headers}")


        return chain.proceed(request)


    }

}