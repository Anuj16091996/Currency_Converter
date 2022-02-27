package com.example.myapplication_discorg_album.Network

import com.example.myapplication_discorg_album.entities.ResultOfRestCountries
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://restcountries.com/"
private val retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()


interface RestCountriesInterface {
    @GET("v2/all/")
    fun getSearchResults(

    ): Call <ArrayList<ResultOfRestCountries>>
}

object RestCountryAPI {
    val RETROFIT_SERVICE: RestCountriesInterface by lazy {
        retrofit.create(RestCountriesInterface::class.java)

    }
}