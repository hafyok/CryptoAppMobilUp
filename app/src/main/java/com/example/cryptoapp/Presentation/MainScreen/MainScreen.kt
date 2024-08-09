package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToAnotherScreen: () -> Unit // Если нужно перейти на другой экран
) {
    val state by viewModel.state.collectAsState()
    val cryptoList by viewModel.cryptoList.collectAsState()
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(id = R.string.list_crypto),
                            fontWeight = FontWeight.Medium
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                CurrencyChips(
                    selectedCurrency = state.selectedCurrency,
                    onCurrencySelected = viewModel::onCurrencySelected,
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
                    state.isLoading -> LoadingScreen()
                    state.isError -> ErrorContent(onRetry = { })
                    else -> {
                        // Текст пока только для теста
                        Text(text = cryptoList[9].name.toString())
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
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.padding(horizontal = 15.dp)
    ) {
        listOf("USD", "RUB").forEach { currency ->
            val isSelected = currency == selectedCurrency
            FilterChip(
                selected = isSelected,
                onClick = { onCurrencySelected(currency) },  // Обновляем состояние при нажатии
                label = { Text(currency, modifier = Modifier.padding(horizontal = 10.dp)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0x41FF9F00), // Цвет выделенного чипа
                    selectedLabelColor = Color(0xFFFFAD25),
                    containerColor = Color.LightGray,
                    labelColor = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(0.dp, color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoScreenError() {
    val viewModel = remember { MainViewModel() }
    val navController = rememberNavController()
    MainScreen(
        viewModel = viewModel,
        onNavigateToAnotherScreen = {
            // Пример навигации на другой экран
            navController.navigate("detail_screen")
        }
    )
}
