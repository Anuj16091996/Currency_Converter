package com.example.application_convercy_converter.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reza.roomapplication.db.entities.CurrencyBook

@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM CurrencyBook")
    fun getAllUser(): MutableList<CurrencyBook>

    @Query("SELECT * FROM CurrencyBook where FromCountry like :fromCountryName")
    fun getUserLive(fromCountryName: String): LiveData<CurrencyBook>

    @Query("SELECT COUNT(*) FROM CurrencyBook where FromCountry like :fromCountryName and FromCurrency like :fromCurrency")
    fun authenticate(fromCountryName: String, fromCurrency: String): Int

    @Query("SELECT * FROM currencybook where id = :id")
    fun getUser(id: Long): LiveData<CurrencyBook>

    @Insert()
    fun insert(currencyBook: CurrencyBook): Long

    @Query("Delete from CurrencyBook where FromImage=:currencyBook and FromAmount=:fromAmount")
    fun delete(currencyBook: String?, fromAmount: Double?)

    @Update
    fun update(vararg currencyBook: CurrencyBook)


}