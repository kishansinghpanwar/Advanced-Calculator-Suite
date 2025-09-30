package com.example.calculator.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility class for common calculator operations
 */
object CalculatorUtils {
    
    // Decimal formatters
    val DECIMAL_FORMAT_2 = DecimalFormat("#,##0.00")
    val DECIMAL_FORMAT_4 = DecimalFormat("#,##0.0000")
    val DECIMAL_FORMAT_10 = DecimalFormat("#.##########")
    
    // Date formatter
    val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    /**
     * Format number with 2 decimal places
     */
    fun formatCurrency(value: Double): String {
        return DECIMAL_FORMAT_2.format(value)
    }
    
    /**
     * Format number with 4 decimal places
     */
    fun formatPrecise(value: Double): String {
        return DECIMAL_FORMAT_4.format(value)
    }
    
    /**
     * Format number with 10 decimal places
     */
    fun formatScientific(value: Double): String {
        return DECIMAL_FORMAT_10.format(value)
    }
    
    /**
     * Check if a number is valid (not NaN or infinite)
     */
    fun isValidNumber(value: Double): Boolean {
        return !value.isNaN() && !value.isInfinite()
    }
    
    /**
     * Safe division that handles division by zero
     */
    fun safeDivide(dividend: Double, divisor: Double): Double {
        return if (divisor != 0.0) dividend / divisor else Double.NaN
    }
    
    /**
     * Calculate percentage
     */
    fun calculatePercentage(value: Double, percentage: Double): Double {
        return value * (percentage / 100.0)
    }
    
    /**
     * Calculate percentage change
     */
    fun calculatePercentageChange(oldValue: Double, newValue: Double): Double {
        return if (oldValue != 0.0) ((newValue - oldValue) / oldValue) * 100.0 else 0.0
    }
    
    /**
     * Get current date as string
     */
    fun getCurrentDateString(): String {
        return DATE_FORMAT.format(Date())
    }
    
    /**
     * Parse date string safely
     */
    fun parseDate(dateString: String): Date? {
        return try {
            DATE_FORMAT.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Calculate factorial
     */
    fun factorial(n: Double): Double {
        if (n < 0 || n != n.toInt().toDouble()) return Double.NaN
        if (n <= 1) return 1.0
        var result = 1.0
        for (i in 2..n.toInt()) {
            result *= i
        }
        return result
    }
    
    /**
     * Convert degrees to radians
     */
    fun degreesToRadians(degrees: Double): Double {
        return degrees * Math.PI / 180.0
    }
    
    /**
     * Convert radians to degrees
     */
    fun radiansToDegrees(radians: Double): Double {
        return radians * 180.0 / Math.PI
    }
}

