package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class FinanceCalculatorActivity : AppCompatActivity() {
    
    private lateinit var presentValue: EditText
    private lateinit var futureValue: EditText
    private lateinit var interestRate: EditText
    private lateinit var timePeriod: EditText
    private lateinit var calculateButton: TextView
    private lateinit var resultText: TextView
    private lateinit var backButton: ImageButton
    private lateinit var calculationType: Spinner
    
    private val decimalFormat = DecimalFormat("#,##0.00")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_calculator)
        
        initializeViews()
        setupClickListeners()
        setupSpinner()
    }
    
    private fun initializeViews() {
        presentValue = findViewById(R.id.presentValue)
        futureValue = findViewById(R.id.futureValue)
        interestRate = findViewById(R.id.interestRate)
        timePeriod = findViewById(R.id.timePeriod)
        calculateButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)
        backButton = findViewById(R.id.backButton)
        calculationType = findViewById(R.id.calculationType)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculateFinance()
        }
        
        backButton.setOnClickListener {
            finish()
        }
    }
    
    private fun setupSpinner() {
        val types = arrayOf("Future Value", "Present Value", "Interest Rate", "Time Period")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        calculationType.adapter = adapter
    }
    
    private fun calculateFinance() {
        try {
            val pv = presentValue.text.toString().toDoubleOrNull() ?: 0.0
            val fv = futureValue.text.toString().toDoubleOrNull() ?: 0.0
            val rate = interestRate.text.toString().toDoubleOrNull() ?: 0.0
            val time = timePeriod.text.toString().toDoubleOrNull() ?: 0.0
            
            val selectedType = calculationType.selectedItem.toString()
            
            when (selectedType) {
                "Future Value" -> {
                    if (pv <= 0 || rate < 0 || time <= 0) {
                        resultText.text = "Please enter valid values"
                        return
                    }
                    val result = pv * Math.pow(1 + rate/100, time)
                    resultText.text = "Future Value: ₹${decimalFormat.format(result)}"
                }
                "Present Value" -> {
                    if (fv <= 0 || rate < 0 || time <= 0) {
                        resultText.text = "Please enter valid values"
                        return
                    }
                    val result = fv / Math.pow(1 + rate/100, time)
                    resultText.text = "Present Value: ₹${decimalFormat.format(result)}"
                }
                "Interest Rate" -> {
                    if (pv <= 0 || fv <= 0 || time <= 0) {
                        resultText.text = "Please enter valid values"
                        return
                    }
                    val result = (Math.pow(fv/pv, 1.0/time) - 1) * 100
                    resultText.text = "Interest Rate: ${decimalFormat.format(result)}%"
                }
                "Time Period" -> {
                    if (pv <= 0 || fv <= 0 || rate <= 0) {
                        resultText.text = "Please enter valid values"
                        return
                    }
                    val result = Math.log(fv/pv) / Math.log(1 + rate/100)
                    resultText.text = "Time Period: ${decimalFormat.format(result)} years"
                }
            }
            
        } catch (e: Exception) {
            resultText.text = "Error calculating finance values"
        }
    }
}
