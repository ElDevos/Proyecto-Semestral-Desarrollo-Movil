// ProductDetailScreen.kt
package com.example.loginretrofit.presentation.productdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.TopBar

@Composable
fun ProductDetailScreen(navController: NavHostController, productId: String) {
    Scaffold(
        topBar = { TopBar("Detalle del producto", navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            Text("Producto: $productId", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Descripción del producto aquí...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

