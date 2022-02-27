package com.example.application_convercy_converter.recycleView

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.R
import com.reza.roomapplication.db.entities.CurrencyBook
import com.squareup.picasso.Picasso

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val dataset = mutableListOf<CurrencyBook>()

    class FavoriteViewHolder(
        private val parent: FavoriteAdapter,
        private val containerView: View
    ) : RecyclerView.ViewHolder(containerView), View.OnCreateContextMenuListener {
        companion object {
            const val EXTRA_INTENT_FAVROITE = "Gibberish"
        }

        var position: CurrencyBook? = null
        val click: CardView = containerView.findViewById(R.id.favroite_click)
        val fromcurrencyCode: TextView =
            containerView.findViewById(R.id.Favroitre_FromCountryCurrency)
        val fromcurrencyValue: TextView =
            containerView.findViewById(R.id.Favroitre_FromCountryAmount)
        val fromCountryName: TextView = containerView.findViewById(R.id.Favroitre_FromCountryName)
        val fromCountryImage: ImageView = containerView.findViewById(R.id.favroite_FromImage)
        val tocurrencyCode: TextView = containerView.findViewById(R.id.Favroitre_ToCountryCurrency)
        val tocurrencyValue: TextView = containerView.findViewById(R.id.Favroitre_ToCountryAmount)
        val toCountryName: TextView = containerView.findViewById(R.id.Favroitre_ToCountryName)
        val toCountryImage: ImageView = containerView.findViewById(R.id.favroite_ToImage)
        private val context: Context = fromCountryName.context
        val menu = containerView.setOnCreateContextMenuListener(this)

        init {
            click.setOnClickListener {
                val intent = Intent(context, Reciving_Currency_Activity::class.java)
                intent.putExtra(EXTRA_INTENT_FAVROITE, position)
                context.startActivity(intent)
            }

        }

        override fun onCreateContextMenu(
            p0: ContextMenu?,
            p1: View?,
            p2: ContextMenu.ContextMenuInfo?
        ) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context).apply {
                setTitle("Select Your Options")
            }
            builder.setPositiveButton("Details", this::userSelect)
            builder.show()
        }

        private fun userSelect(dialog: DialogInterface, which: Int) {
            when (which) {

                DialogInterface.BUTTON_POSITIVE -> {
                    val intent = Intent(context, Reciving_Currency_Activity::class.java)
                    intent.putExtra(EXTRA_INTENT_FAVROITE, position)
                    context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FavoriteAdapter.FavoriteViewHolder(this, view)
    }

    fun changeData(data: MutableList<CurrencyBook>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.fromcurrencyCode.setText(currentData.FromCurrency)
        holder.fromcurrencyValue.setText(currentData.FromAmount.toString())
        holder.fromCountryName.setText(currentData.FromCountry)
        Picasso.get().load(currentData.FromImage).into(holder.fromCountryImage)
        holder.tocurrencyCode.setText(currentData.ToCurrency)
        holder.tocurrencyValue.setText(currentData.ToAmount.toString())
        holder.toCountryName.setText(currentData.ToCountry)
        Picasso.get().load(currentData.ToImage).into(holder.toCountryImage)
        holder.position = dataset[position]
    }

    override fun getItemCount() = dataset.count()
    override fun getItemViewType(position: Int): Int {
        return R.layout.favroite_custom
    }
}