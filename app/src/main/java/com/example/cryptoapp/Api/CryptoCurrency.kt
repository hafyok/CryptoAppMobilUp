package com.example.cryptoapp.Api

import com.google.gson.annotations.SerializedName

data class CryptoCurrency(
    @SerializedName("id"                               ) var id                           : String? = null,
    @SerializedName("symbol"                           ) var symbol                       : String? = null,
    @SerializedName("name"                             ) var name                         : String? = null,
    @SerializedName("image"                            ) var image                        : String? = null,
    @SerializedName("current_price"                    ) var currentPrice                 : Double?    = null,
    @SerializedName("price_change_percentage_24h"      ) var priceChangePercentage24h     : Double? = null,
)
