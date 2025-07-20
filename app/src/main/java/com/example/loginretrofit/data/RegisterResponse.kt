package com.example.loginretrofit.data

import com.example.loginretrofit.data.remote.User

data class RegisterResponse(
    val token: String,
    val user: User
)
