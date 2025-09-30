package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class BMICalculatorActivity : AppCompatActivity() {
    
    private lateinit var height: EditText
    private lateinit var weight: EditText
    private lateinit var resultText: TextView
    private lateinit var calculateButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var unitSpinner: Spinner
    
    private val decimalFormat = DecimalFormat("#.##")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        
        initializeViews()
        setupClickListeners()
        setupSpinner()
    }
    
    private fun initializeViews() {
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        backButton = findViewById(R.id.backButton)
        unitSpinner = findViewById(R.id.unitSpinner)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculateBMI()
        }
        
        backButton.setOnClickListener {
            finish()
        }
    }
    
    private fun setupSpinner() {
        val units = arrayOf("Metric (kg, cm)", "Imperial (lbs, ft/in)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
    }
    
    private fun calculateBMI() {
        try {
            val heightValue = height.text.toString().toDoubleOrNull() ?: 0.0
            val weightValue = weight.text.toString().toDoubleOrNull() ?: 0.0
            
            if (heightValue <= 0 || weightValue <= 0) {
                resultText.text = "Please enter valid height and weight"
                return
            }
            
            val selectedUnit = unitSpinner.selectedItem.toString()
            val bmi: Double
            val heightInMeters: Double
            
            if (selectedUnit == "Metric (kg, cm)") {
                heightInMeters = heightValue / 100.0
                bmi = weightValue / (heightInMeters * heightInMeters)
            } else {
                // Imperial: convert feet and inches to meters
                val feet = heightValue.toInt()
                val inches = ((heightValue - feet) * 100).toInt()
                heightInMeters = (feet * 12 + inches) * 0.0254
                bmi = (weightValue * 0.453592) / (heightInMeters * heightInMeters)
            }
            
            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal weight"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }
            
            resultText.text = """
                BMI: ${decimalFormat.format(bmi)}
                Category: $category
                
                BMI Ranges:
                • Underweight: < 18.5
                • Normal: 18.5 - 24.9
                • Overweight: 25 - 29.9
                • Obese: ≥ 30
            """.trimIndent()
            
        } catch (e: Exception) {
            resultText.text = "Error calculating BMI"
        }
    }
}
