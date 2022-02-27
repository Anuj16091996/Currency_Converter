package com.example.application_convercy_converter.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.R
import com.example.application_convercy_converter.entities.CountryDetails

class CountriesAdapter() : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {
    private val dataset = mutableListOf<CountryDetails>()

    class CountriesViewHolder(
        private val parent: CountriesAdapter,
        private val containerView: View
    ) :
        RecyclerView.ViewHolder(containerView) {


        var position: CountryDetails? = null
        private val click: CardView = containerView.findViewById(R.id.item_click)
        val currencyCode: TextView = containerView.findViewById(R.id.itemList_CurrencyCode)
        val currencyValue: TextView = containerView.findViewById(R.id.itemList_CurrencyValue)


        init {
            click.setOnClickListener {
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CountriesViewHolder(this, view)
    }


    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.currencyCode.setText(currentData.currencyName)
        holder.currencyValue.setText(currentData.currencyCode)
        holder.position = dataset[position]

    }


    fun changeData(data: MutableList<CountryDetails>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount() = dataset.count()
    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item
    }


}