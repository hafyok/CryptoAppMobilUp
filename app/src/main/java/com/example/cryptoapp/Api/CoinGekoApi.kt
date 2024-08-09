package com.example.cryptoapp.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getCryptoCurrencies(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): List<CryptoCurrency>
}