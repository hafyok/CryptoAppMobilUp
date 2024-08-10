package com.example.cryptoapp.Api

import com.google.gson.annotations.SerializedName

data class CryptoDetails(
    @SerializedName("id"                    ) var id                 : String?           = null,
    @SerializedName("name"                  ) var name               : String?           = null,
    @SerializedName("web_slug"              ) var webSlug            : String?           = null,
    @SerializedName("categories"            ) var categories         : ArrayList<String> = arrayListOf(),
    @SerializedName("description"           ) var description        : Description?      = Description(),
    @SerializedName("image"                 ) var image              : Image?            = Image()
)

data class Description (
    @SerializedName("en"    ) var en    : String? = null,
)

data class Image (
    @SerializedName("thumb" ) var thumb : String? = null,
    @SerializedName("small" ) var small : String? = null,
    @SerializedName("large" ) var large : String? = null
)