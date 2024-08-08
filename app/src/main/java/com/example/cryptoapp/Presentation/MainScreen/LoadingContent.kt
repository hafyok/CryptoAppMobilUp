package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptoapp.ui.theme.Orange

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
            color = Orange // Оранжевый цвет индикатора
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoScreenLoading() {
    var selectedCurrency by remember { mutableStateOf("USD") }
    MainScreen(
        isLoading = true,
        isError = false,
        selectedCurrency = selectedCurrency,
        onCurrencySelected = { newCurrency ->
            selectedCurrency = newCurrency  // Обновляем состояние выбранной валюты
        },
        onRetry = {}
    )
}