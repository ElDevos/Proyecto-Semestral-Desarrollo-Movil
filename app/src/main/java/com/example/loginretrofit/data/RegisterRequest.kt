package com.example.loginretrofit.data

data class RegisterRequest(
    val name: String,
    val lastname: String,
    val email: String,
    val password: String
)
