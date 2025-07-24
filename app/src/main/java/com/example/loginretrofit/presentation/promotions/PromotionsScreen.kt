package com.example.loginretrofit.presentation.promotions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginretrofit.R
import com.example.loginretrofit.presentation.components.TopBar
import com.example.loginretrofit.presentation.components.BottomNavigationBar
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Promotion(val id: String, val title: String, val description: String, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PromotionsScreen(navController: NavHostController) {
    val promos = listOf(
        Promotion("1", "2x1 en Jugos", "Llévate 2 jugos al precio de 1 solo este fin de semana", R.drawable.jugo),
        Promotion("2", "Descuento en Carnes", "Hasta 25% en carnes seleccionadas", R.drawable.carnes_pescados),
        Promotion("3", "Oferta en Frutas", "Bananas y manzanas a solo $0.50 la libra", R.drawable.frutas_verduras)
    )

    Scaffold(
        topBar = { TopBar("Promociones", navController) },
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(promos) { promo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(promo.imageRes),
                            contentDescription = promo.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = promo.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = promo.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}