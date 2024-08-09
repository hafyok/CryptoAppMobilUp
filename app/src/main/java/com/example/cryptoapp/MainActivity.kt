package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cryptoapp.Api.CoinGeckoApi
import com.example.cryptoapp.Api.RetrofitClient
import com.example.cryptoapp.ui.theme.CryptoAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val cryptoApi = RetrofitClient.getInstance().create(CoinGeckoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val offers = cryptoApi.getCryptoCurrencies("usd")
                Log.d("CRYPTO", offers.toString())
            } catch (e: Exception) {
                Log.e("NOT_CRYPTO", e.toString())
            }
        }

        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {

            }
        }
    }
}
