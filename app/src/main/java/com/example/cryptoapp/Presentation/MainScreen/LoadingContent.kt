package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color(0xFFFFA500) // Оранжевый цвет индикатора
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoScreenLoading() {
    MainScreen(
        isLoading = true,
        isError = false,
        selectedCurrency = "RUS",
        onCurrencySelected = {},
        onRetry = {}
    )
}