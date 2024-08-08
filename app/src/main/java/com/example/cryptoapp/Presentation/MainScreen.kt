package com.example.cryptoapp.Presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(
    isLoading: Boolean,
    isError: Boolean,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
    onRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                TopAppBar(
                    title = {
                        Text("Список криптовалют", fontWeight = FontWeight.Medium)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                CurrencyChips(
                    selectedCurrency = selectedCurrency,
                    onCurrencySelected = onCurrencySelected,
                    /*modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp) // сдвиг чипов влево и вниз*/
                )
                HorizontalDivider(modifier = Modifier.shadow(2.dp))
            }
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                when {
                    isLoading -> LoadingScreen()
                    isError -> ErrorScreen(onRetry = onRetry)
                    else -> {
                        // Здесь будет основной контент
                    }
                }
            }
        }
    )
}

@Composable
fun CurrencyChips(
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        listOf("USD", "RUB").forEach { currency ->
            val isSelected = currency == selectedCurrency
            FilterChip(
                selected = isSelected,
                onClick = { onCurrencySelected(currency) },
                label = { Text(currency, modifier = Modifier.padding(horizontal = 10.dp), color = Color.Black) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0x41FF9F00), // Цвет выделенного чипа
                    selectedLabelColor = Color(0xFFFFAD25),
                    containerColor = Color.LightGray,
                    labelColor = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}


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

@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Замените на реальный ресурс
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Произошла какая-то ошибка :(",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ПОПРОБОВАТЬ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoScreenLoading() {
    CryptoScreen(
        isLoading = true,
        isError = false,
        selectedCurrency = "USD",
        onCurrencySelected = {},
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoScreenError() {
    CryptoScreen(
        isLoading = false,
        isError = true,
        selectedCurrency = "USD",
        onCurrencySelected = {},
        onRetry = {}
    )
}