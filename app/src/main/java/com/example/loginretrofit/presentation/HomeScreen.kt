// HomeScreen.kt
package com.example.loginretrofit.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.example.loginretrofit.presentation.components.BottomNavigationBar
import com.example.loginretrofit.presentation.components.TopBar
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Category(val id: String, val name: String, val imageRes: Int)
data class Product(val id: String, val name: String, val description: String, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val categories = listOf(
        Category("1", "Frutas y Verduras", R.drawable.frutas_verduras),
        Category("2", "Galletas y Panadería", R.drawable.panaderia),
        Category("3", "Carnes, Pollos y Pescados", R.drawable.carnes_pescados),
        Category("4", "Bebidas sin Alcohol", R.drawable.jugo),
        Category("5", "Limpieza", R.drawable.limpieza),
        Category("6", "Snacks y Frutos secos", R.drawable.snacks),
        Category("7", "Higiene y Cuidado", R.drawable.higiene),
        Category("8", "Congelados", R.drawable.hielo),
        Category("9", "Jamones y Embutidos", R.drawable.embutidos),
        Category("10", "Golosinas y Chocolates", R.drawable.golosinas),
        Category("11", "Bebés", R.drawable.pamper),
        Category("12", "Mascotas", R.drawable.mascotas)
    )

    val images = listOf(R.drawable.jugo, R.drawable.panaderia, R.drawable.embutidos)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            scope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Scaffold(
        topBar = { TopBar("Supermercados Rey", navController, showBackButton = false) },
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {

            item {
                Column {
                    HorizontalPager(
                        count = images.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 8.dp)
                    ) { page ->
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Image(
                                painter = painterResource(id = images[page]),
                                contentDescription = "Slide $page",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                    )
                }
            }

            items(categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("category/${category.id}")
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row {
                        Image(
                            painter = painterResource(category.imageRes),
                            contentDescription = category.name,
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = category.name,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
