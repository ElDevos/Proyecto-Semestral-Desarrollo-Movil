package com.example.loginretrofit.data.remote

data class User(
    val id: Int,
    val name: String,
    val lastname: String,
    val email: String,
    val updated_at: String,
    val created_at: String
)
