package com.example.cryptoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.Presentation.MainScreen.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            var selectedCurrency by remember { mutableStateOf("USD") } // костыль
            MainScreen(
                isLoading = true,
                isError = false,
                selectedCurrency = selectedCurrency,
                onCurrencySelected = { newCurrency ->
                    selectedCurrency = newCurrency
                },
                onRetry = {}
                // TODO() сократить кол-во передаваемых параметров
            )
        }

        composable("detail_screen") {
            // Экран с дополнительной информацией
        }
    }
}