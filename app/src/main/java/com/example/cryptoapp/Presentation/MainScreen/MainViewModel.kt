package com.example.cryptoapp.Presentation.MainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.Api.CoinGeckoApi
import com.example.cryptoapp.Api.CryptoCurrency
import com.example.cryptoapp.Api.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state

    private val _cryptoList = MutableStateFlow(listOf<CryptoCurrency>())
    val cryptoList: StateFlow<List<CryptoCurrency>> = _cryptoList

    private val _currentCurrency = MutableStateFlow(_state.value.selectedCurrency.lowercase())
    val currentCurrency: StateFlow<String> = _currentCurrency

    private val _iconCurrency = MutableStateFlow(if (_currentCurrency.value == "usd") "$" else "Р")
    val iconCurrency: StateFlow<String> = _iconCurrency

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    init {
        getCrypto(currentCurrency.value)
    }

    fun onCurrencySelected(currency: String) {
        _state.value = _state.value.copy(selectedCurrency = currency)
        _currentCurrency.value = _state.value.selectedCurrency.lowercase()
        _iconCurrency.value = if (_currentCurrency.value == "usd") "$" else "Р"

        Log.d("CurrencySelected", _currentCurrency.value)
        getCrypto(currentCurrency.value)
    }

    fun retry() {
        _isRefreshing.value = true
        getCrypto(currentCurrency.value)
    }

    private fun getCrypto(currency: String) {
        _state.value = _state.value.copy(isLoading = true, isError = false)
        val cryptoApi = RetrofitClient.getInstance().create(CoinGeckoApi::class.java)
        viewModelScope.launch {
            try {
                delay(1000L)
                val response = cryptoApi.getCryptoCurrencies(currency)
                _cryptoList.value = response
                _state.value = _state.value.copy(isLoading = false, isError = false)
            } catch (e: Exception) {
                Log.e("NOT_CRYPTO", e.toString())
                _state.value = _state.value.copy(isLoading = false, isError = true)
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}

data class MainScreenState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var selectedCurrency: String = "USD"
)