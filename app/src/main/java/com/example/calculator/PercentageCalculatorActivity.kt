package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PercentageCalculatorActivity : AppCompatActivity() {
    
    private lateinit var originalValue: EditText
    private lateinit var percentage: EditText
    private lateinit var resultText: TextView
    private lateinit var calculateButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var calculationType: Spinner
    
    private val decimalFormat = DecimalFormat("#,##0.00")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percentage_calculator)
        
        initializeViews()
        setupClickListeners()
        setupSpinner()
    }
    
    private fun initializeViews() {
        originalValue = findViewById(R.id.originalValue)
        percentage = findViewById(R.id.percentage)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        backButton = findViewById(R.id.backButton)
        calculationType = findViewById(R.id.calculationType)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculatePercentage()
        }
        
        backButton.setOnClickListener {
            finish()
        }
    }
    
    private fun setupSpinner() {
        val types = arrayOf("What is X% of Y?", "X is what % of Y?", "X is Y% of what?", "Percentage increase/decrease")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        calculationType.adapter = adapter
    }
    
    private fun calculatePercentage() {
        try {
            val value1 = originalValue.text.toString().toDoubleOrNull() ?: 0.0
            val value2 = percentage.text.toString().toDoubleOrNull() ?: 0.0
            
            val selectedType = calculationType.selectedItem.toString()
            
            when (selectedType) {
                "What is X% of Y?" -> {
                    val result = (value1 * value2) / 100
                    resultText.text = "${decimalFormat.format(value2)}% of ${decimalFormat.format(value1)} = ${decimalFormat.format(result)}"
                }
                "X is what % of Y?" -> {
                    val result = (value1 / value2) * 100
                    resultText.text = "${decimalFormat.format(value1)} is ${decimalFormat.format(result)}% of ${decimalFormat.format(value2)}"
                }
                "X is Y% of what?" -> {
                    val result = (value1 / value2) * 100
                    resultText.text = "${decimalFormat.format(value1)} is ${decimalFormat.format(value2)}% of ${decimalFormat.format(result)}"
                }
                "Percentage increase/decrease" -> {
                    val result = ((value2 - value1) / value1) * 100
                    val change = if (result >= 0) "increased" else "decreased"
                    resultText.text = "Value ${change} by ${decimalFormat.format(kotlin.math.abs(result))}%"
                }
            }
            
        } catch (e: Exception) {
            resultText.text = "Error calculating percentage"
        }
    }
}
