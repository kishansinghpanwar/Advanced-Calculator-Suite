package com.example.calculator.calculators

import com.example.calculator.constants.CalculatorConstants
import com.example.calculator.utils.CalculatorUtils

/**
 * Percentage Calculator implementation
 */
object PercentageCalculator {
    
    /**
     * Calculate percentage of a number
     */
    fun calculatePercentageOf(value: Double, percentage: Double): PercentageCalculationResult {
        if (value < 0 || percentage < 0) {
            return PercentageCalculationResult.error("Values must be non-negative")
        }
        
        val result = CalculatorUtils.calculatePercentage(value, percentage)
        val percentageValue = result
        
        return PercentageCalculationResult(
            value = value,
            percentage = percentage,
            result = result,
            calculationType = CalculatorConstants.CALC_TYPE_PERCENTAGE_OF
        )
    }
    
    /**
     * Calculate percentage change
     */
    fun calculatePercentageChange(oldValue: Double, newValue: Double): PercentageCalculationResult {
        if (oldValue < 0 || newValue < 0) {
            return PercentageCalculationResult.error("Values must be non-negative")
        }
        
        val percentage = CalculatorUtils.calculatePercentageChange(oldValue, newValue)
        val change = newValue - oldValue
        
        return PercentageCalculationResult(
            value = oldValue,
            percentage = percentage,
            result = newValue,
            calculationType = CalculatorConstants.CALC_TYPE_PERCENTAGE_CHANGE,
            change = change
        )
    }
    
    /**
     * Calculate percentage increase
     */
    fun calculatePercentageIncrease(oldValue: Double, newValue: Double): PercentageCalculationResult {
        if (oldValue < 0 || newValue < 0) {
            return PercentageCalculationResult.error("Values must be non-negative")
        }
        
        val percentage = if (oldValue != 0.0) ((newValue - oldValue) / oldValue) * 100.0 else 0.0
        val increase = newValue - oldValue
        
        return PercentageCalculationResult(
            value = oldValue,
            percentage = percentage,
            result = newValue,
            calculationType = CalculatorConstants.CALC_TYPE_PERCENTAGE_INCREASE,
            change = increase
        )
    }
    
    /**
     * Calculate percentage decrease
     */
    fun calculatePercentageDecrease(oldValue: Double, newValue: Double): PercentageCalculationResult {
        if (oldValue < 0 || newValue < 0) {
            return PercentageCalculationResult.error("Values must be non-negative")
        }
        
        val percentage = if (oldValue != 0.0) ((oldValue - newValue) / oldValue) * 100.0 else 0.0
        val decrease = oldValue - newValue
        
        return PercentageCalculationResult(
            value = oldValue,
            percentage = percentage,
            result = newValue,
            calculationType = CalculatorConstants.CALC_TYPE_PERCENTAGE_DECREASE,
            change = decrease
        )
    }
    
    /**
     * Data class for percentage calculation result
     */
    data class PercentageCalculationResult(
        val value: Double,
        val percentage: Double,
        val result: Double,
        val calculationType: String,
        val change: Double? = null,
        val error: String? = null
    ) {
        fun isError(): Boolean = error != null
        
        fun getFormattedValue(): String = CalculatorUtils.formatCurrency(value)
        fun getFormattedPercentage(): String = CalculatorUtils.formatPrecise(percentage)
        fun getFormattedResult(): String = CalculatorUtils.formatCurrency(result)
        fun getFormattedChange(): String = change?.let { CalculatorUtils.formatCurrency(it) } ?: ""
        
        companion object {
            fun error(message: String): PercentageCalculationResult {
                return PercentageCalculationResult(0.0, 0.0, 0.0, "", error = message)
            }
        }
    }
}

