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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.Presentation.ErrorContent
import com.example.cryptoapp.Presentation.LoadingScreen
import com.example.cryptoapp.R
import com.example.cryptoapp.ui.theme.Black
import com.example.cryptoapp.ui.theme.DarkOrange
import com.example.cryptoapp.ui.theme.LightGrey
import com.example.cryptoapp.ui.theme.LightOrange
import com.example.cryptoapp.ui.theme.White
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToAnotherScreen: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val cryptoList by viewModel.cryptoList.collectAsState()
    val iconCurrency by viewModel.iconCurrency.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
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
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = White),
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
                Spacer(modifier = Modifier.height(8.dp))

                when {
                    state.isLoading && !isRefreshing->
                        LoadingScreen()

                    state.isError -> ErrorContent(onRetry = { viewModel.retry() })
                    else -> {
                        SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.retry() }) {
                            LazyColumn {
                                items(cryptoList) { crypto ->
                                    CryptoListItem(
                                        icon = crypto.image.toString(),
                                        description = crypto.symbol.toString(),
                                        title = crypto.name.toString(),
                                        price = String.format(
                                            Locale.getDefault(),
                                            "%.2f",
                                            crypto.currentPrice
                                        ),
                                        percent = String.format(
                                            Locale.getDefault(),
                                            "%.2f",
                                            crypto.priceChangePercentage24h
                                        ),
                                        currency = iconCurrency,
                                        id = crypto.id.toString(), // Передаем id валюты
                                        onClick = { cryptoId -> onNavigateToAnotherScreen(cryptoId) } // Передаем id при нажатии
                                    )
                                }
                            }
                        }
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
                onClick = { onCurrencySelected(currency) },
                label = { Text(currency, modifier = Modifier.padding(horizontal = 15.dp)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = DarkOrange, // Цвет выделенного чипа
                    selectedLabelColor = LightOrange,
                    containerColor = LightGrey,
                    labelColor = Black
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(0.dp, color = Color.White)
            )
        }
    }
}
