package com.example.myapplication_discorg_album.entities

import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

data class  ResultOfRestCountries(
    @SerializedName("name") val countryName: String,
    @SerializedName("flags") val flags:CountryDetailsFromAPI,
    @SerializedName("currencies") val currencies:ArrayList<CountryDetailsFromAPI>,
    )

{

}