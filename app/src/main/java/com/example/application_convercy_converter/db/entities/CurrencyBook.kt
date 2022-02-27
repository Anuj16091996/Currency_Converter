package com.reza.roomapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CurrencyBook(
    @ColumnInfo val FromImage: String?,
    @ColumnInfo var ToImage: String?,
    @ColumnInfo var FromAmount: Double?,
    @ColumnInfo var FromCountry: String?,
    @ColumnInfo var FromCurrency: String?,
    @ColumnInfo var ToAmount: Double?,
    @ColumnInfo var ToCountry: String?,
    @ColumnInfo var ToCurrency: String?,
):Serializable  {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}