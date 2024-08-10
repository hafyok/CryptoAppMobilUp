package com.example.cryptoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.Presentation.DetailScreen.DetailScreen
import com.example.cryptoapp.Presentation.DetailScreen.DetailViewModel
import com.example.cryptoapp.Presentation.DetailScreen.DetailViewModelFactory
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
                onNavigateToAnotherScreen = { cryptoId ->
                    navController.navigate("detail_screen/$cryptoId") // Передаем id валюты в маршрут
                }
            )
        }

        composable("detail_screen/{cryptoId}") { backStackEntry ->
            val cryptoId = backStackEntry.arguments?.getString("cryptoId") ?: return@composable
            val viewModel: DetailViewModel = viewModel(
                factory = DetailViewModelFactory(cryptoId)
            )
            viewModel.coinDetail.value.name?.let {
                DetailScreen(
                    isLoading = viewModel.state.value.isLoading,
                    isError = viewModel.state.value.isError,
                    title = it,
                    onRetry = { viewModel.getCryptoDetails(cryptoId) },
                    navigateBack = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
        }
    }
}