package com.example.calculator.calculators

import com.example.calculator.constants.CalculatorConstants
import com.example.calculator.utils.CalculatorUtils

/**
 * BMI Calculator implementation
 */
object BMICalculator {
    
    /**
     * Calculate BMI
     */
    fun calculateBMI(weight: Double, height: Double, isMetric: Boolean = true): BMICalculationResult {
        if (weight <= 0 || height <= 0) {
            return BMICalculationResult.error("Weight and height must be positive")
        }
        
        val weightKg = if (isMetric) weight else weight * 0.453592 // Convert lbs to kg
        val heightM = if (isMetric) height / 100 else height * 0.3048 // Convert cm to m or ft to m
        
        val bmi = weightKg / (heightM * heightM)
        val category = getBMICategory(bmi)
        
        return BMICalculationResult(
            bmi = bmi,
            category = category,
            weight = weightKg,
            height = heightM
        )
    }
    
    /**
     * Get BMI category
     */
    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < CalculatorConstants.BMI_UNDERWEIGHT_MAX -> CalculatorConstants.BMI_UNDERWEIGHT
            bmi <= CalculatorConstants.BMI_NORMAL_MAX -> CalculatorConstants.BMI_NORMAL
            bmi <= CalculatorConstants.BMI_OVERWEIGHT_MAX -> CalculatorConstants.BMI_OVERWEIGHT
            else -> CalculatorConstants.BMI_OBESE
        }
    }
    
    /**
     * Data class for BMI calculation result
     */
    data class BMICalculationResult(
        val bmi: Double,
        val category: String,
        val weight: Double,
        val height: Double,
        val error: String? = null
    ) {
        fun isError(): Boolean = error != null
        
        fun getFormattedBMI(): String = CalculatorUtils.formatPrecise(bmi)
        fun getFormattedWeight(): String = CalculatorUtils.formatPrecise(weight)
        fun getFormattedHeight(): String = CalculatorUtils.formatPrecise(height)
        
        companion object {
            fun error(message: String): BMICalculationResult {
                return BMICalculationResult(0.0, "", 0.0, 0.0, message)
            }
        }
    }
}

