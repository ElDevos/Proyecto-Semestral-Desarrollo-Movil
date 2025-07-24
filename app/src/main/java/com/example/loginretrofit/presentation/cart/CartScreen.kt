// CartScreen.kt
package com.example.loginretrofit.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.TopBar

@Composable
fun CartScreen(navController: NavHostController) {
    val cartItems = remember { mutableStateListOf("Manzana", "Leche", "Arroz") }

    Scaffold(
        topBar = { TopBar("Carrito", navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            if (cartItems.isEmpty()) {
                Text("Tu carrito está vacío.")
            } else {
                cartItems.forEach { item ->
                    Text("- $item", modifier = Modifier.padding(vertical = 4.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    navController.navigate("checkout")
                }) {
                    Text("Proceder al pago")
                }
            }
        }
    }
}

