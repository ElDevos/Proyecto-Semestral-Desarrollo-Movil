package com.example.loginretrofit.data.remote

import com.example.loginretrofit.data.LoginRequest
import com.example.loginretrofit.data.LoginResponse
import com.example.loginretrofit.data.RegisterRequest
import com.example.loginretrofit.data.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}