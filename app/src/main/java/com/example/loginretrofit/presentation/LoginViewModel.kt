package com.example.loginretrofit.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.loginretrofit.data.LoginRequest
import com.example.loginretrofit.data.RegisterRequest
import com.example.loginretrofit.data.local.TokenManager
import com.example.loginretrofit.data.remote.RetrofitInstance

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)

    fun login(context: Context) {
        errorMessage = null
        if (!email.contains("@")) {
            errorMessage = "Correo inválido"
            return
        }
        if (password.length < 8) {
            errorMessage = "La contraseña debe tener al menos 8 caracteres"
            return
        }

        viewModelScope.launch {
            isLoading = true
            val tokenManager = TokenManager(context)

            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (!token.isNullOrEmpty()) {
                        tokenManager.saveToken(token)
                        isLoggedIn = true
                    } else {
                        errorMessage = "Token no recibido"
                    }
                } else {
                    errorMessage = "Login fallido: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.localizedMessage}"
            }
            isLoading = false
        }
    }

    fun register(
        name: String,
        lastname: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.register(
                    RegisterRequest(name, lastname, email, password)
                )
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error ${response.code()}: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Error: ${e.localizedMessage}")
            }
        }
    }
}
