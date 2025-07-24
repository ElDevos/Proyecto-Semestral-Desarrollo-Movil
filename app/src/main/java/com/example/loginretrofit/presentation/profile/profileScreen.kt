package com.example.loginretrofit.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.BottomNavigationBar
import com.example.loginretrofit.presentation.components.TopBar


@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()
    val isUpdating by viewModel.isUpdating.collectAsState()
    val updateMessage by viewModel.updateMessage.collectAsState()

    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadUser()
    }

    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        val currentUser = user
        if (currentUser != null && !initialized) {
            name = currentUser.name
            lastname = currentUser.lastname
            email = currentUser.email
            initialized = true
        }
    }

    Scaffold(
        topBar = { TopBar("Mi Perfil", navController) },
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    label = { Text("Apellido") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.updateUser(name, lastname, email)
                    },
                    enabled = !isUpdating,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isUpdating) "Actualizando..." else "Actualizar perfil")
                }

                if (updateMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(updateMessage!!, color = MaterialTheme.colorScheme.primary)
                }
            }

            Button(
                onClick = {
                    viewModel.logout {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
