package com.karamlyy.currencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.karamlyy.currencyapp.databinding.ActivityMainBinding
import com.karamlyy.currencyapp.models.Currency
import com.karamlyy.currencyapp.spinnerListeners.FromSpinnerListener
import com.karamlyy.currencyapp.spinnerListeners.ToSpinnerListener
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    val retrofit = RetrofitInstance.getRetrofitInstance()
    val apiService = retrofit.create(ApiService::class.java)

    private lateinit var fromSpinner : Spinner
    private lateinit var toSpinner : Spinner
    private lateinit var spinnerAdapter : com.karamlyy.currencyapp.adapters.SpinnerAdapter

    lateinit var exchangeRateText : TextView
    lateinit var enteredAmount: EditText
    lateinit var convertButton: Button
    lateinit var convertedAmountView: TextView
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

        enteredAmount = findViewById(R.id.enteredAmount)
        convertButton = findViewById(R.id.convertButton)
        convertedAmountView = findViewById(R.id.convertedAmountView)
        exchangeRateText = findViewById(R.id.exchangeRateText)
        fromSpinner=findViewById(R.id.fromAmountSpinner)
        toSpinner=findViewById(R.id.toAmountSpinner)
        spinnerAdapter=com.karamlyy.currencyapp.adapters.SpinnerAdapter(this,list,names)
        fromSpinner.adapter=spinnerAdapter
        toSpinner.adapter=spinnerAdapter
        fromSpinner.onItemSelectedListener = FromSpinnerListener(list)
        toSpinner.onItemSelectedListener = ToSpinnerListener(list)
        getData()

    }

    fun getData(){
        //Api key : gZtI9xikDbj79Upk914b6A
        //Url https://fcsapi.com/
        // api-v3/forex/latest?symbol=${currencyFromString}/${currencyToString}&access_key=gZtI9xikDbj79Upk914b6A
        val apiKey = "gZtI9xikDbj79Upk914b6A"

        runBlocking {
            launch {
                async {
                    try {
                        val result = apiService.getForexList(
                            symbol = "${currencyFromString}/${currencyToString}",
                            accessKey = apiKey
                        )
                        val rate = result.response.first().c.toDouble()
                        val amount = enteredAmount.text.toString().toDouble()
                        val converted = rate * amount

                        runOnUiThread {

                            exchangeRateText.text = "1 ${currencyFromString} = ${String. format("%.4f", rate)} ${currencyToString}"
                            convertedAmountView.text = String. format("%.3f", converted)
                        }

                    } catch (error:Throwable){
                        if (currencyFromString == currencyToString){
                           Toast.makeText(this@MainActivity, "same rate", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}