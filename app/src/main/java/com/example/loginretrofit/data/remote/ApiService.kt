package com.example.loginretrofit.data.remote

import com.example.loginretrofit.data.LoginRequest
import com.example.loginretrofit.data.LoginResponse
import com.example.loginretrofit.data.RegisterRequest
import com.example.loginretrofit.data.RegisterResponse
import com.example.loginretrofit.data.UpdateUserRequest
import com.example.loginretrofit.data.UpdateUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("logout")
    suspend fun logout(@Header("Authorization") token: String): Response<Unit>

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): Response<User>

    @PUT("updateuser")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Body request: UpdateUserRequest
    ): Response<UpdateUserResponse>
}