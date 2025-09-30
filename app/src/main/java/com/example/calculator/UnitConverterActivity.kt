package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class UnitConverterActivity : AppCompatActivity() {
    
    private lateinit var fromValue: EditText
    private lateinit var toValue: TextView
    private lateinit var fromUnit: Spinner
    private lateinit var toUnit: Spinner
    private lateinit var convertButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var categorySpinner: Spinner
    
    private val decimalFormat = DecimalFormat("#,##0.0000")
    
    private val categories = mapOf(
        "Length" to listOf("Meter", "Kilometer", "Centimeter", "Millimeter", "Inch", "Foot", "Yard", "Mile"),
        "Weight" to listOf("Kilogram", "Gram", "Pound", "Ounce", "Ton", "Stone"),
        "Temperature" to listOf("Celsius", "Fahrenheit", "Kelvin"),
        "Area" to listOf("Square Meter", "Square Kilometer", "Square Foot", "Square Inch", "Acre", "Hectare"),
        "Volume" to listOf("Liter", "Milliliter", "Gallon", "Quart", "Pint", "Cup", "Fluid Ounce")
    )
    
    private val conversionRates = mapOf(
        // Length conversions (to meters)
        "Meter" to 1.0,
        "Kilometer" to 1000.0,
        "Centimeter" to 0.01,
        "Millimeter" to 0.001,
        "Inch" to 0.0254,
        "Foot" to 0.3048,
        "Yard" to 0.9144,
        "Mile" to 1609.344,
        
        // Weight conversions (to kilograms)
        "Kilogram" to 1.0,
        "Gram" to 0.001,
        "Pound" to 0.453592,
        "Ounce" to 0.0283495,
        "Ton" to 1000.0,
        "Stone" to 6.35029,
        
        // Temperature conversions (special handling)
        "Celsius" to 1.0,
        "Fahrenheit" to 1.0,
        "Kelvin" to 1.0,
        
        // Area conversions (to square meters)
        "Square Meter" to 1.0,
        "Square Kilometer" to 1000000.0,
        "Square Foot" to 0.092903,
        "Square Inch" to 0.00064516,
        "Acre" to 4046.86,
        "Hectare" to 10000.0,
        
        // Volume conversions (to liters)
        "Liter" to 1.0,
        "Milliliter" to 0.001,
        "Gallon" to 3.78541,
        "Quart" to 0.946353,
        "Pint" to 0.473176,
        "Cup" to 0.236588,
        "Fluid Ounce" to 0.0295735
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_converter)
        
        initializeViews()
        setupClickListeners()
        setupSpinners()
    }
    
    private fun initializeViews() {
        fromValue = findViewById(R.id.fromValue)
        toValue = findViewById(R.id.toValue)
        fromUnit = findViewById(R.id.fromUnit)
        toUnit = findViewById(R.id.toUnit)
        convertButton = findViewById(R.id.convertButton)
        backButton = findViewById(R.id.backButton)
        categorySpinner = findViewById(R.id.categorySpinner)
    }
    
    private fun setupClickListeners() {
        convertButton.setOnClickListener {
            convertUnits()
        }
        
        backButton.setOnClickListener {
            finish()
        }
        
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                updateUnitSpinners()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupSpinners() {
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories.keys.toList())
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter
        
        updateUnitSpinners()
    }
    
    private fun updateUnitSpinners() {
        val selectedCategory = categorySpinner.selectedItem.toString()
        val units = categories[selectedCategory] ?: emptyList()
        
        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromUnit.adapter = unitAdapter
        toUnit.adapter = unitAdapter
    }
    
    private fun convertUnits() {
        try {
            val value = fromValue.text.toString().toDoubleOrNull() ?: 0.0
            val fromUnitStr = fromUnit.selectedItem.toString()
            val toUnitStr = toUnit.selectedItem.toString()
            
            if (value < 0) {
                toValue.text = "Please enter a positive value"
                return
            }
            
            val result = when (categorySpinner.selectedItem.toString()) {
                "Temperature" -> convertTemperature(value, fromUnitStr, toUnitStr)
                else -> convertStandard(value, fromUnitStr, toUnitStr)
            }
            
            toValue.text = decimalFormat.format(result)
            
        } catch (e: Exception) {
            toValue.text = "Error converting units"
        }
    }
    
    private fun convertStandard(value: Double, fromUnit: String, toUnit: String): Double {
        val fromRate = conversionRates[fromUnit] ?: 1.0
        val toRate = conversionRates[toUnit] ?: 1.0
        
        // Convert to base unit first, then to target unit
        val baseValue = value * fromRate
        return baseValue / toRate
    }
    
    private fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            fromUnit == toUnit -> value
            fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (value * 9.0/5.0) + 32
            fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (value - 32) * 5.0/9.0
            fromUnit == "Celsius" && toUnit == "Kelvin" -> value + 273.15
            fromUnit == "Kelvin" && toUnit == "Celsius" -> value - 273.15
            fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> (value - 32) * 5.0/9.0 + 273.15
            fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> (value - 273.15) * 9.0/5.0 + 32
            else -> value
        }
    }
}