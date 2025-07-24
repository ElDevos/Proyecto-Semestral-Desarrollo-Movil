package com.example.loginretrofit.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary, // Fondo amarillo, si usas ese color
        contentColor = Color.White // Color de texto e íconos
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio", tint = Color.White) },
            label = { Text("Inicio", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("promotions") },
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Promociones", tint = Color.White) },
            label = { Text("Promociones", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("orders") },
            icon = { Icon(Icons.Filled.List, contentDescription = "Pedidos", tint = Color.White) },
            label = { Text("Pedidos", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Perfil", tint = Color.White) },
            label = { Text("Perfil", color = Color.White) }
        )
    }
}

