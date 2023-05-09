package com.karamlyy.currencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.Toast
import com.karamlyy.currencyapp.databinding.ActivityMainBinding
import com.karamlyy.currencyapp.models.Currency
import com.karamlyy.currencyapp.spinnerListeners.FromSpinnerListener
import com.karamlyy.currencyapp.spinnerListeners.ToSpinnerListener

class MainActivity : AppCompatActivity() {

    private lateinit var fromSpinner : Spinner
    private lateinit var toSpinner : Spinner
    private lateinit var spinnerAdapter : com.karamlyy.currencyapp.adapters.SpinnerAdapter

    companion object{
        var currencyFromString : String = ""
        var currencyToString : String = ""
    }

    private lateinit var binding: ActivityMainBinding
    private var list = mutableListOf<Currency>(
        Currency(R.drawable.azn,"AZN"),
        Currency(R.drawable.tryy,"TRY"),
        Currency(R.drawable.eur,"EUR"),
        Currency(R.drawable.usd,"USD"),
        Currency(R.drawable.jpy,"JPY"),
        Currency(R.drawable.gbp,"GBP"),
        Currency(R.drawable.cny,"CNY"),
        Currency(R.drawable.aud,"AUD"),
        Currency(R.drawable.cad,"CAD"),
        Currency(R.drawable.rub,"RUB"))
    private var names : List<String> = mutableListOf("AZN","TRY","EUR","USD","JPY","GBP","CNY","AUD","CAD","RUB")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fromSpinner=findViewById(R.id.fromAmountSpinner)
        toSpinner=findViewById(R.id.toAmountSpinner)
        spinnerAdapter=com.karamlyy.currencyapp.adapters.SpinnerAdapter(this,list,names)
        fromSpinner.adapter=spinnerAdapter
        toSpinner.adapter=spinnerAdapter
        fromSpinner.onItemSelectedListener = FromSpinnerListener(list)
        toSpinner.onItemSelectedListener = ToSpinnerListener(list)
        clickListener()
        getData()

    }
    fun getData(){
        //Api key : gZtI9xikDbj79Upk914b6A
        //Url https://fcsapi.com/api-v3/forex/latest?symbol=${from}/${to}&access_key=gZtI9xikDbj79Upk914b6A


    }
    fun clickListener(){
        binding.convertButton.setOnClickListener {
            Log.d("Check","From "+currencyFromString)
            Log.d("Check","To "+currencyToString)
        }
    }
}