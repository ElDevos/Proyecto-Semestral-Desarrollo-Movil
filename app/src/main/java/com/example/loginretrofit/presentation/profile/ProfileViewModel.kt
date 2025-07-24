package com.example.loginretrofit.presentation.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginretrofit.data.UpdateUserRequest
import com.example.loginretrofit.data.local.TokenManager
import com.example.loginretrofit.data.remote.ApiService
import com.example.loginretrofit.data.remote.RetrofitInstance
import com.example.loginretrofit.data.remote.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val tokenManager = TokenManager(context)
    private val apiService: ApiService = RetrofitInstance.api

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    var isUpdating = MutableStateFlow(false)
    var updateMessage = MutableStateFlow<String?>(null)

    fun loadUser() {
        viewModelScope.launch {
            val token = tokenManager.getToken()
            token?.let {
                val response = apiService.getUser("Bearer $it")
                if (response.isSuccessful) {
                    _user.value = response.body()
                }
            }
        }
    }

    fun updateUser(name: String, lastname: String, email: String) {
        viewModelScope.launch {
            isUpdating.value = true
            val token = tokenManager.getToken()
            if (token != null && _user.value != null) {
                val request = UpdateUserRequest(name, lastname, email)
                val response = apiService.updateUser("Bearer $token", request)
                if (response.isSuccessful && response.body()?.success == true) {
                    _user.value = response.body()?.user
                    updateMessage.value = response.body()?.message ?: "Perfil actualizado"
                } else {
                    updateMessage.value = "Error al actualizar: ${response.code()} ${response.message()}"
                }
            }
            isUpdating.value = false
        }
    }

    fun logout(onLogout: () -> Unit) {
        viewModelScope.launch {
            val token = tokenManager.getToken()
            if (token != null) {
                try {
                    apiService.logout("Bearer $token")
                } catch (_: Exception) { }
                tokenManager.clearToken()
                onLogout()
            }
        }
    }
}
