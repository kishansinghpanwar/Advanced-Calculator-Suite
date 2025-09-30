package com.example.calculator.activities

import android.os.Bundle
import android.widget.EditText
import com.example.calculator.R
import com.example.calculator.base.BaseInputCalculatorActivity
import com.example.calculator.calculators.EMICalculator
import com.example.calculator.constants.CalculatorConstants
import com.example.calculator.utils.ValidationUtils

/**
 * Refactored EMI Calculator Activity
 * Uses the new structured approach with base classes and calculators
 */
class RefactoredEMICalculatorActivity : BaseInputCalculatorActivity() {
    
    private lateinit var principalAmount: EditText
    private lateinit var interestRate: EditText
    private lateinit var loanTenure: EditText
    
    override fun getLayoutResource(): Int = R.layout.activity_emi_calculator
    
    override fun initializeViews() {
        super.initializeViews()
        principalAmount = findViewById(R.id.principalAmount)
        interestRate = findViewById(R.id.interestRate)
        loanTenure = findViewById(R.id.loanTenure)
    }
    
    override fun setupCalculator() {
        // Setup EMI calculator specific functionality
    }
    
    override fun onCalculateClick() {
        // Validate inputs
        val validationError = ValidationUtils.validateAll(
            principalAmount to "Principal Amount",
            interestRate to "Interest Rate",
            loanTenure to "Loan Tenure"
        )
        
        if (validationError != null) {
            showError(validationError)
            return
        }
        
        // Get input values
        val principal = principalAmount.text.toString().toDoubleOrNull() ?: 0.0
        val rate = interestRate.text.toString().toDoubleOrNull() ?: 0.0
        val tenure = loanTenure.text.toString().toDoubleOrNull() ?: 0.0
        
        // Calculate EMI
        val result = EMICalculator.calculateEMI(principal, rate, tenure)
        
        if (result.isError()) {
            showError(result.error ?: CalculatorConstants.ERROR_CALCULATION_FAILED)
        } else {
            showResult(formatEMIResult(result))
        }
    }
    
    private fun formatEMIResult(result: EMICalculator.EMICalculationResult): String {
        return """
            EMI: ${result.getFormattedEMI()}
            Total Payment: ${result.getFormattedTotalPayment()}
            Total Interest: ${result.getFormattedTotalInterest()}
            Principal: ${result.getFormattedPrincipal()}
            Tenure: ${result.tenureMonths} months
        """.trimIndent()
    }
}

