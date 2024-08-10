package com.example.cryptoapp.Presentation.DetailScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.Api.CoinGeckoApi
import com.example.cryptoapp.Api.RetrofitClient
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    init {
        getCryptoDetails()
    }

    fun getCryptoDetails(){
        val cryptoApi = RetrofitClient.getInstance().create(CoinGeckoApi::class.java)
        viewModelScope.launch {
            try {
                val response = cryptoApi.getCoinDetails("ethereum")
                //_cryptoList.value = response
                //_state.value = _state.value.copy(isLoading = false, isError = false)
                Log.d("CRYPTO_INFO", response.toString())
            } catch (e: Exception) {
                Log.e("NOT_CRYPTO", e.toString())
                //_state.value = _state.value.copy(isLoading = false, isError = true)
            }
        }
    }
}