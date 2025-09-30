package com.example.calculator.calculators

import com.example.calculator.utils.CalculatorUtils

/**
 * Tip Calculator implementation
 */
object TipCalculator {
    
    /**
     * Calculate tip and total amount
     */
    fun calculateTip(billAmount: Double, tipPercentage: Double, numberOfPeople: Int): TipCalculationResult {
        if (billAmount <= 0 || tipPercentage < 0 || numberOfPeople <= 0) {
            return TipCalculationResult.error("Invalid input values")
        }
        
        val tipAmount = CalculatorUtils.calculatePercentage(billAmount, tipPercentage)
        val totalAmount = billAmount + tipAmount
        val tipPerPerson = tipAmount / numberOfPeople
        val totalPerPerson = totalAmount / numberOfPeople
        
        return TipCalculationResult(
            billAmount = billAmount,
            tipPercentage = tipPercentage,
            tipAmount = tipAmount,
            totalAmount = totalAmount,
            numberOfPeople = numberOfPeople,
            tipPerPerson = tipPerPerson,
            totalPerPerson = totalPerPerson
        )
    }
    
    /**
     * Data class for tip calculation result
     */
    data class TipCalculationResult(
        val billAmount: Double,
        val tipPercentage: Double,
        val tipAmount: Double,
        val totalAmount: Double,
        val numberOfPeople: Int,
        val tipPerPerson: Double,
        val totalPerPerson: Double,
        val error: String? = null
    ) {
        fun isError(): Boolean = error != null
        
        fun getFormattedBillAmount(): String = CalculatorUtils.formatCurrency(billAmount)
        fun getFormattedTipAmount(): String = CalculatorUtils.formatCurrency(tipAmount)
        fun getFormattedTotalAmount(): String = CalculatorUtils.formatCurrency(totalAmount)
        fun getFormattedTipPerPerson(): String = CalculatorUtils.formatCurrency(tipPerPerson)
        fun getFormattedTotalPerPerson(): String = CalculatorUtils.formatCurrency(totalPerPerson)
        
        companion object {
            fun error(message: String): TipCalculationResult {
                return TipCalculationResult(0.0, 0.0, 0.0, 0.0, 0, 0.0, 0.0, message)
            }
        }
    }
}

