package com.example.loginretrofit.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginretrofit.presentation.cart.CartScreen
import com.example.loginretrofit.presentation.category.CategoryScreen
import com.example.loginretrofit.presentation.checkout.CheckoutScreen
import com.example.loginretrofit.presentation.home.HomeScreen
import com.example.loginretrofit.presentation.orderhistory.OrderHistoryScreen
import com.example.loginretrofit.presentation.productdetail.ProductDetailScreen
import com.example.loginretrofit.presentation.profile.ProfileScreen
import com.example.loginretrofit.presentation.promotions.PromotionsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("category/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            CategoryScreen(navController, categoryId)
        }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }
        composable("cart") { CartScreen(navController) }
        composable("checkout") { CheckoutScreen(navController) }
        composable("orders") { OrderHistoryScreen(navController) }
        composable("promotions") { PromotionsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }


    }

}