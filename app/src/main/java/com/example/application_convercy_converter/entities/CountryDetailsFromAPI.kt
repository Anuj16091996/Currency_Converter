package com.example.myapplication_discorg_album.entities

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class CountryDetailsFromAPI(
    @SerializedName("svg") val flagSVG: String,
    @SerializedName("png") val flagPNG: String,
    @SerializedName("code") val currencyCode: String,
    @SerializedName("name") val currencyName: String
) : Serializable {
}