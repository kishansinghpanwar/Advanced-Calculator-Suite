package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class EMICalculatorActivity : AppCompatActivity() {
    
    private lateinit var principalAmount: EditText
    private lateinit var interestRate: EditText
    private lateinit var loanTenure: EditText
    private lateinit var calculateButton: TextView
    private lateinit var resultText: TextView
    private lateinit var backButton: ImageButton
    
    private val decimalFormat = DecimalFormat("#,##0.00")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emi_calculator)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        principalAmount = findViewById(R.id.principalAmount)
        interestRate = findViewById(R.id.interestRate)
        loanTenure = findViewById(R.id.loanTenure)
        calculateButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)
        backButton = findViewById(R.id.backButton)
    }
    
    private fun setupClickListeners() {
        calculateButton.setOnClickListener {
            calculateEMI()
        }
        
        backButton.setOnClickListener {
            finish()
        }
    }
    
    private fun calculateEMI() {
        try {
            val principal = principalAmount.text.toString().toDoubleOrNull() ?: 0.0
            val rate = interestRate.text.toString().toDoubleOrNull() ?: 0.0
            val tenure = loanTenure.text.toString().toDoubleOrNull() ?: 0.0
            
            if (principal <= 0 || rate < 0 || tenure <= 0) {
                resultText.text = "Please enter valid values"
                return
            }
            
            val monthlyRate = rate / (12 * 100)
            val tenureMonths = tenure * 12
            
            val emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) / 
                     (Math.pow(1 + monthlyRate, tenureMonths) - 1)
            
            val totalPayment = emi * tenureMonths
            val totalInterest = totalPayment - principal
            
            resultText.text = """
                EMI: ₹${decimalFormat.format(emi)}
                Total Payment: ₹${decimalFormat.format(totalPayment)}
                Total Interest: ₹${decimalFormat.format(totalInterest)}
            """.trimIndent()
            
        } catch (e: Exception) {
            resultText.text = "Error calculating EMI"
        }
    }
}
