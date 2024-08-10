package com.example.cryptoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.Presentation.DetailScreen.DetailScreen
import com.example.cryptoapp.Presentation.MainScreen.MainScreen
import com.example.cryptoapp.Presentation.MainScreen.MainViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            val viewModel = remember { MainViewModel() }
            MainScreen(
                viewModel = viewModel,
                onNavigateToAnotherScreen = {
                    navController.navigate("detail_screen")
                }
            )
        }

        composable("detail_screen") {
            // Экран с дополнительной информацией
            DetailScreen(
                isLoading = false,
                isError = false,
                title = "Bitcoin",
                onRetry = { /*TODO*/ }) {

            }
        }
    }
}