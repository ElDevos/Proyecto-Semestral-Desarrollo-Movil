// CheckoutScreen.kt
package com.example.loginretrofit.presentation.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.TopBar

@Composable
fun CheckoutScreen(navController: NavHostController) {
    var confirmed by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar("Confirmar pedido", navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            if (!confirmed) {
                Text("Resumen del pedido:")
                Text("- Manzana\n- Leche\n- Arroz")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { confirmed = true }) {
                    Text("Confirmar")
                }
            } else {
                Text("✅ Pedido confirmado. Gracias por tu compra.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }) {
                    Text("Volver al inicio")
                }
            }
        }
    }
}


