package com.karamlyy.currencyapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.karamlyy.currencyapp.R
import com.karamlyy.currencyapp.models.Currency

class SpinnerAdapter(contextt: Context,list : List<Currency> , names : List<String>) : ArrayAdapter<String>(contextt, R.layout.spinner_item,R.id.spinnerCurrencyTextView,names) {
    var contextt : Context
    var list : List<Currency>
    var names : List<String>
    init {
        this.contextt=contextt
        this.list=list
        this.names = names
    }

    @SuppressLint("MissingInflatedId")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = contextt.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row : View = inflater.inflate(R.layout.spinner_item,null)
        val currencyImage : ImageView= row.findViewById(R.id.spinnerCurrencyImageView)
        val currencyName : TextView = row.findViewById(R.id.spinnerCurrencyTextView)

        currencyImage.setImageResource(list.get(position).image)
        currencyName.setText(list.get(position).name)

        return row
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = contextt.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row : View = inflater.inflate(R.layout.spinner_item,null)
        val currencyImage : ImageView= row.findViewById(R.id.spinnerCurrencyImageView)
        val currencyName : TextView = row.findViewById(R.id.spinnerCurrencyTextView)

        currencyImage.setImageResource(list.get(position).image)
        currencyName.setText(list.get(position).name)

        return row
    }
}