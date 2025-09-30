package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class TipCalculatorActivity : AppCompatActivity() {
    
    private lateinit var billAmount: EditText
    private lateinit var tipPercentage: EditText
    private lateinit var numberOfPeople: EditText
    private lateinit var resultText: TextView
    private lateinit var calculateButton: TextView
    private lateinit var backButton: ImageButton
    
    private val decimalFormat = DecimalFormat("#,##0.00")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        billAmount = findViewById(R.id.billAmount)
        tipPercentage = findViewById(R.id.tipPercentage)
        numberOfPeople = findViewById(R.id.numberOfPeople)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        backButton = findViewById(R.id.backButton)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculateTip()
        }
        
        backButton.setOnClickListener {
            finish()
        }
    }
    
    private fun calculateTip() {
        try {
            val bill = billAmount.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercent = tipPercentage.text.toString().toDoubleOrNull() ?: 0.0
            val people = numberOfPeople.text.toString().toIntOrNull() ?: 1
            
            if (bill <= 0) {
                resultText.text = "Please enter a valid bill amount"
                return
            }
            
            val tipAmount = (bill * tipPercent) / 100
            val totalBill = bill + tipAmount
            val perPerson = totalBill / people
            
            resultText.text = """
                Bill Amount: $${decimalFormat.format(bill)}
                Tip Amount: $${decimalFormat.format(tipAmount)}
                Total Bill: $${decimalFormat.format(totalBill)}
                Per Person: $${decimalFormat.format(perPerson)}
            """.trimIndent()
            
        } catch (e: Exception) {
            resultText.text = "Error calculating tip"
        }
    }
}
