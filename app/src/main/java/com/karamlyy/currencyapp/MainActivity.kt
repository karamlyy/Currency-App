package com.karamlyy.currencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enteredAmount: EditText = findViewById(R.id.enteredAmount)
        val convertButton: Button = findViewById(R.id.convertButton)
        val convertedAmountView: TextView = findViewById(R.id.convertedAmountView)
        convertButton.setOnClickListener {
            val enteredValue: String = enteredAmount.text.toString()
            convertedAmountView.text = enteredValue
        }
    }
}