// OrderHistoryScreen.kt
package com.example.loginretrofit.presentation.orderhistory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.TopBar

data class Order(val id: String, val date: String, val total: String)

@Composable
fun OrderHistoryScreen(navController: NavHostController) {
    val mockOrders = listOf(
        Order("1", "2025-07-20", "$15.00"),
        Order("2", "2025-07-18", "$27.35"),
        Order("3", "2025-07-10", "$10.90")
    )

    Scaffold(
        topBar = { TopBar("Historial de pedidos", navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            items(mockOrders) { order ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text("Pedido #${order.id}", style = MaterialTheme.typography.titleMedium)
                    Text("Fecha: ${order.date}")
                    Text("Total: ${order.total}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

