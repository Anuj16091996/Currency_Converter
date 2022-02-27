package com.example.application_convercy_converter.recycleView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.application_convercy_converter.MainActivity
import com.example.application_convercy_converter.Network.ConvertAPI
import com.example.application_convercy_converter.R
import com.example.application_convercy_converter.db.AppDatabase
import com.example.application_convercy_converter.entities.AmeraDonConversion
import com.example.application_convercy_converter.entities.CountryDetails
import com.reza.roomapplication.db.entities.CurrencyBook
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class Reciving_Currency_Activity : AppCompatActivity(), retrofit2.Callback<AmeraDonConversion> {
    private lateinit var fromImage: ImageView
    private lateinit var toImage: ImageView
    private lateinit var fromAmount: TextView
    private lateinit var fromCountryName: TextView
    private lateinit var fromCurrencyName: TextView
    private lateinit var toAmount: TextView
    private lateinit var toCountryName: TextView
    private lateinit var toCurrencyName: TextView
    private lateinit var bookCurrency: Button
    private var userAmount: String? = null
    var userAmountDouble: Double? = 0.001
    private lateinit var dataBase: AppDatabase
    var convertAmount: Double? = null
    var bookCurrencyBoolean = true
    var bookDataBoolean = true
    private var detailsFromDatabase = CurrencyBook(
        null, null, null, null, null,
        null, null, null
    )
    private var fromUserChoice = CountryDetails(null, null, null, null)
    private var toUserChoice = CountryDetails(null, null, null, null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciving_currency)
        fromImage = findViewById(R.id.reciving_fromImage)
        toImage = findViewById(R.id.reciving_ToImage)
        fromAmount = findViewById(R.id.reciving_fromamount)
        fromCountryName = findViewById(R.id.reciving_fromcountryName)
        fromCurrencyName = findViewById(R.id.reciving_fromcurrencyName)
        toAmount = findViewById(R.id.reciving_Toamount)
        toCountryName = findViewById(R.id.reciving_TocountryName)
        toCurrencyName = findViewById(R.id.reciving_TOcurrencyName)
        dataBase = AppDatabase.getDatabaseInstance(this)
        bookCurrency = findViewById(R.id.recieve_book)
        bookCurrency.setOnClickListener(this::BookCurrency)
        val reciveArrow = findViewById<ImageView>(R.id.recive_arraow)
        reciveArrow.rotation = reciveArrow.rotation + 90
        val intent = intent
        if (intent.hasExtra(MainActivity.TO_USER_CHOICE)) {
            fromUserChoice =
                intent.getSerializableExtra(MainActivity.FROM_USER_CHOICE) as CountryDetails
            toUserChoice =
                intent.getSerializableExtra(MainActivity.TO_USER_CHOICE) as CountryDetails
            userAmount = intent.getStringExtra(MainActivity.USER_Amount)
            userAmountDouble = userAmount?.toDouble()
            val getApi = ConvertAPI.RETROFIT_SERVICE.getSearchResults(
                fromUserChoice.currencyCode.toString(),
                toUserChoice.currencyCode.toString(), userAmountDouble
            )
            getApi.enqueue(this)
        }

        if (intent.hasExtra(FavoriteAdapter.FavoriteViewHolder.EXTRA_INTENT_FAVROITE)) {
            detailsFromDatabase =
                intent.getSerializableExtra(FavoriteAdapter.FavoriteViewHolder.EXTRA_INTENT_FAVROITE) as CurrencyBook
            Picasso.get().load(detailsFromDatabase.FromImage).into(fromImage)
            Picasso.get().load(detailsFromDatabase.ToImage).into(toImage)
            fromAmount.setText(detailsFromDatabase.FromAmount.toString())
            fromCountryName.setText(detailsFromDatabase.FromCountry)
            fromCurrencyName.setText(detailsFromDatabase.FromCurrency)
            toCountryName.setText(detailsFromDatabase.ToCountry)
            toCurrencyName.setText(detailsFromDatabase.ToCurrency)
            toAmount.setText(detailsFromDatabase.ToAmount.toString())
            bookCurrencyBoolean = false
            bookDataBoolean = false
            bookCurrency.setText("Remove")
        }
    }

    private fun BookCurrency(view: View) {

        if (bookCurrencyBoolean) {

            AppDatabase.databaseWriteExecutor.execute {
                dataBase.CurrencyDAO().insert(
                    CurrencyBook(
                        fromUserChoice.countryImage.toString(),
                        toUserChoice.countryImage.toString(),
                        userAmountDouble,
                        fromUserChoice.countryName.toString(),
                        fromUserChoice.currencyName.toString(),
                        convertAmount,
                        toUserChoice.countryName.toString(),
                        toUserChoice.currencyName.toString()
                    )
                )
            }
            bookCurrency.setText("Remove")
            Toast.makeText(this, "Currency Book", Toast.LENGTH_SHORT)
                .show()
            bookCurrencyBoolean = false
        } else {

            if (!bookDataBoolean) {

                AppDatabase.databaseWriteExecutor.execute {
                    dataBase.CurrencyDAO()
                        .delete(detailsFromDatabase.FromImage, detailsFromDatabase.FromAmount)
                }
                Toast.makeText(this, "Currency Removed", Toast.LENGTH_SHORT).show()
                bookCurrencyBoolean = true
                bookCurrency.setText("Add")
            } else {
                AppDatabase.databaseWriteExecutor.execute {
                    val id =
                        dataBase.CurrencyDAO().delete(fromUserChoice.countryImage, userAmountDouble)
                }
                Toast.makeText(this, "Currency Removed", Toast.LENGTH_SHORT).show()
                bookCurrencyBoolean = true
                bookCurrency.setText("Add")
            }


        }

    }

    override fun onResponse(
        call: Call<AmeraDonConversion>,
        response: Response<AmeraDonConversion>
    ) {
        val Tempdata = response.body()
        if (Tempdata != null) {
            convertAmount = Tempdata.currencyAmount
        }
        Picasso.get().load(fromUserChoice.countryImage).into(fromImage)
        Picasso.get().load(toUserChoice.countryImage).into(toImage)
        fromAmount.setText(userAmountDouble.toString())
        fromCountryName.setText(fromUserChoice.countryName)
        fromCurrencyName.setText(fromUserChoice.currencyCode)
        toCountryName.setText(toUserChoice.countryName)
        toCurrencyName.setText(toUserChoice.currencyName)
        toAmount.setText(convertAmount.toString())

    }

    override fun onFailure(call: Call<AmeraDonConversion>, t: Throwable) {
        println(t)
    }
}