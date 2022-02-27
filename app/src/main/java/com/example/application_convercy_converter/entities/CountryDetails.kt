package com.example.application_convercy_converter.entities

import java.io.Serializable

data class CountryDetails(
    val countryName: String?,
    var countryImage: String?,
    var currencyCode: String?,
    var currencyName:String?
):Serializable {
}