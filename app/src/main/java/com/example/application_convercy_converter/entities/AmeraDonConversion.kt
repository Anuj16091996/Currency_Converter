package com.example.application_convercy_converter.entities

import com.google.gson.annotations.SerializedName

data class AmeraDonConversion(
    @SerializedName("conversion_result") var currencyAmount: Double
) {
}