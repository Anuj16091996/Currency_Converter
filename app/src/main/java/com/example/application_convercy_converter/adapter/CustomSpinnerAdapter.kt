package com.example.application_convercy_converter.adapter

import android.app.Activity
import android.content.Context
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.custom_item.*
import com.example.application_convercy_converter.R
import com.example.application_convercy_converter.entities.CountryDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_item.view.*


class CustomSpinnerAdapter(ctx: Context, countries: ArrayList<CountryDetails>) :
    ArrayAdapter<CountryDetails>(ctx, 0, countries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    private fun createItemView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val country = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.custom_item,
            parent,
            false
        )

        country?.let {
            view.custom_text.text = country.countryName
            Picasso.get()
                .load(country.countryImage)
                .into(view.custom_image)
        }

        return view
    }
}