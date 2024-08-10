package com.example.cryptoapp.Presentation.DetailScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.Api.CoinGeckoApi
import com.example.cryptoapp.Api.CryptoDetails
import com.example.cryptoapp.Api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val cryptoId: String) : ViewModel() {
    private val _state = MutableStateFlow(DetailScreenState())
    val state: StateFlow<DetailScreenState> = _state

    private val _coinDetails = MutableStateFlow(CryptoDetails())
    val coinDetail = _coinDetails

    init {
        getCryptoDetails(cryptoId)
    }

    fun retry() {
        getCryptoDetails(cryptoId)
    }

    private fun getCryptoDetails(crypto: String) {
        val cryptoApi = RetrofitClient.getInstance().create(CoinGeckoApi::class.java)
        viewModelScope.launch {
            try {
                val response = cryptoApi.getCoinDetails(crypto)
                _coinDetails.value = response
                _state.value = _state.value.copy(isLoading = false, isError = false)
                Log.d("CRYPTO_INFO", response.toString())
            } catch (e: Exception) {
                Log.e("NOT_CRYPTO", e.toString())
                _state.value = _state.value.copy(isLoading = false, isError = true)
            }
        }
    }
}

data class DetailScreenState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
)