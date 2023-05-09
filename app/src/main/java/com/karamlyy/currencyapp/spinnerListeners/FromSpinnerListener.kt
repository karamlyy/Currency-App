package com.karamlyy.currencyapp.spinnerListeners

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import com.karamlyy.currencyapp.MainActivity
import com.karamlyy.currencyapp.models.Currency

class FromSpinnerListener(list : List<Currency>) : OnItemSelectedListener {
    var list : List<Currency>
    init {
        this.list=list
    }
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, l: Long) {
        MainActivity.currencyFromString=list.get(position).name
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}