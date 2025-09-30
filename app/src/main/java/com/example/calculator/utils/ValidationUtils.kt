package com.example.calculator.utils

import android.widget.EditText

/**
 * Utility class for input validation
 */
object ValidationUtils {
    
    /**
     * Validate if EditText has non-empty text
     */
    fun isNotEmpty(editText: EditText): Boolean {
        return editText.text.toString().trim().isNotEmpty()
    }
    
    /**
     * Validate if EditText has valid number
     */
    fun isValidNumber(editText: EditText): Boolean {
        val text = editText.text.toString().trim()
        if (text.isEmpty()) return false
        
        return try {
            text.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    
    /**
     * Validate if EditText has positive number
     */
    fun isPositiveNumber(editText: EditText): Boolean {
        if (!isValidNumber(editText)) return false
        return editText.text.toString().toDouble() > 0
    }
    
    /**
     * Validate if EditText has non-negative number
     */
    fun isNonNegativeNumber(editText: EditText): Boolean {
        if (!isValidNumber(editText)) return false
        return editText.text.toString().toDouble() >= 0
    }
    
    /**
     * Validate if EditText has valid integer
     */
    fun isValidInteger(editText: EditText): Boolean {
        val text = editText.text.toString().trim()
        if (text.isEmpty()) return false
        
        return try {
            text.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    
    /**
     * Validate if EditText has valid date format (YYYY-MM-DD)
     */
    fun isValidDateFormat(editText: EditText): Boolean {
        val text = editText.text.toString().trim()
        if (text.isEmpty()) return false
        
        return try {
            CalculatorUtils.DATE_FORMAT.parse(text)
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Get error message for validation
     */
    fun getValidationError(editText: EditText, fieldName: String): String? {
        return when {
            !isNotEmpty(editText) -> "$fieldName is required"
            !isValidNumber(editText) -> "$fieldName must be a valid number"
            !isPositiveNumber(editText) -> "$fieldName must be positive"
            !isNonNegativeNumber(editText) -> "$fieldName must be non-negative"
            !isValidInteger(editText) -> "$fieldName must be a valid integer"
            !isValidDateFormat(editText) -> "$fieldName must be in YYYY-MM-DD format"
            else -> null
        }
    }
    
    /**
     * Validate all EditTexts and return first error message
     */
    fun validateAll(vararg editTexts: Pair<EditText, String>): String? {
        for ((editText, fieldName) in editTexts) {
            val error = getValidationError(editText, fieldName)
            if (error != null) return error
        }
        return null
    }
}

