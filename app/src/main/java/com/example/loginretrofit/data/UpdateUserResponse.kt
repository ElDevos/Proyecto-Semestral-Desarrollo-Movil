package com.example.loginretrofit.data

import com.example.loginretrofit.data.remote.User

data class UpdateUserResponse(
    val success: Boolean,
    val message: String,
    val user: User
)
