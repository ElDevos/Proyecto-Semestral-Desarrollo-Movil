package com.example.loginretrofit.data

import com.example.loginretrofit.data.remote.User

data class LoginResponse(
    val token:String,
    val user: User
)
