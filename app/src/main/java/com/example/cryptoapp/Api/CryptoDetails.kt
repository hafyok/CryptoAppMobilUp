package com.example.cryptoapp.Api

import com.google.gson.annotations.SerializedName

data class CryptoDetails(
    @SerializedName("id"                    ) var id                 : String?           = "",
    @SerializedName("name"                  ) var name               : String?           = "",
    @SerializedName("web_slug"              ) var webSlug            : String?           = "",
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
    @SerializedName("thumb" ) var thumb : String? = null,
    @SerializedName("small" ) var small : String? = null,
    @SerializedName("large" ) var large : String? = null
)