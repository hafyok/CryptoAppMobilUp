package com.example.cryptoapp.Presentation.MainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.Api.CoinGeckoApi
import com.example.cryptoapp.Api.CryptoCurrency
import com.example.cryptoapp.Api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state

    private val _cryptoList = MutableStateFlow(listOf<CryptoCurrency>())
    val cryptoList: StateFlow<List<CryptoCurrency>> = _cryptoList

    fun onCurrencySelected(currency: String) {
        _state.value = _state.value.copy(selectedCurrency = currency)
    }

    fun retry() {
        // Логика повтора загрузки данных
    }

    init {
        getCrypto()
    }

    fun getCrypto(){
        val cryptoApi = RetrofitClient.getInstance().create(CoinGeckoApi::class.java)
        viewModelScope.launch {
            try {
                val response = cryptoApi.getCryptoCurrencies("usd")
                Log.d("CRYPTO", response.toString())
                _cryptoList.value = response
                _state.value.isError = false
                _state.value.isLoading = false

            } catch (e: Exception) {
                Log.e("NOT_CRYPTO", e.toString())
                _state.value.isLoading = false
                _state.value.isError = true
            }
        }
    }
}

data class MainScreenState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var selectedCurrency: String = "USD"
)