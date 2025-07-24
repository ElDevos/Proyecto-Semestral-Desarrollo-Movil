// CategoryScreen.kt
package com.example.loginretrofit.presentation.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.presentation.components.TopBar

data class Product(val id: String, val name: String)

@Composable
fun CategoryScreen(navController: NavHostController, categoryId: String) {
    val products = listOf(
        Product("1", "Manzana"),
        Product("2", "Banana"),
        Product("3", "Naranja")
    )

    Scaffold(
        topBar = { TopBar("Categoría $categoryId", navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("product/${product.id}")
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = product.name,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


