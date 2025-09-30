package com.example.calculator.calculators

import com.example.calculator.utils.CalculatorUtils

/**
 * EMI Calculator implementation
 */
object EMICalculator {
    
    /**
     * Calculate EMI (Equated Monthly Installment)
     */
    fun calculateEMI(principal: Double, rate: Double, tenure: Double): EMICalculationResult {
        if (principal <= 0 || rate < 0 || tenure <= 0) {
            return EMICalculationResult.error("Invalid input values")
        }
        
        val monthlyRate = rate / (12 * 100)
        val tenureMonths = tenure * 12
        
        val emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) / 
                 (Math.pow(1 + monthlyRate, tenureMonths) - 1)
        
        val totalPayment = emi * tenureMonths
        val totalInterest = totalPayment - principal
        
        return EMICalculationResult(
            emi = emi,
            totalPayment = totalPayment,
            totalInterest = totalInterest,
            principal = principal,
            tenureMonths = tenureMonths.toInt()
        )
    }
    
    /**
     * Data class for EMI calculation result
     */
    data class EMICalculationResult(
        val emi: Double,
        val totalPayment: Double,
        val totalInterest: Double,
        val principal: Double,
        val tenureMonths: Int,
        val error: String? = null
    ) {
        fun isError(): Boolean = error != null
        
        fun getFormattedEMI(): String = CalculatorUtils.formatCurrency(emi)
        fun getFormattedTotalPayment(): String = CalculatorUtils.formatCurrency(totalPayment)
        fun getFormattedTotalInterest(): String = CalculatorUtils.formatCurrency(totalInterest)
        fun getFormattedPrincipal(): String = CalculatorUtils.formatCurrency(principal)
        
        companion object {
            fun error(message: String): EMICalculationResult {
                return EMICalculationResult(0.0, 0.0, 0.0, 0.0, 0, message)
            }
        }
    }
}

