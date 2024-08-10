package com.example.cryptoapp.Api

import com.google.gson.annotations.SerializedName

data class CryptoDetails(
    @SerializedName("id"                    ) var id                 : String?           = "",
    @SerializedName("name"                  ) var name               : String?           = "",
    @SerializedName("categories"            ) var categories         : ArrayList<String> = arrayListOf(),
    @SerializedName("description"           ) var description        : Description?      = Description(),
    @SerializedName("image"                 ) var image              : Image?            = Image()
)

data class Description (
    @SerializedName("en"    ) var en    : String? = null,
){
    // Для редактирования строки при запросе к ней
    val cleanedDescription: String?
        get() = en?.replace("Description(en=", "")?.replace(")", "")
}

data class Image (
    @SerializedName("large" ) var large : String? = null
)