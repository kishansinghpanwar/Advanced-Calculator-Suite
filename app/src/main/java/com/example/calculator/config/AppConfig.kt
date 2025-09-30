package com.example.calculator.config

import com.example.calculator.constants.CalculatorConstants

/**
 * Application configuration
 */
object AppConfig {
    
    // App information
    const val APP_NAME = "Calculator App"
    const val APP_VERSION = "1.0.0"
    const val APP_PACKAGE = "com.example.calculator"
    
    // Calculator settings
    const val MAX_HISTORY_ITEMS = 100
    const val MAX_DECIMAL_PLACES = 10
    const val DEFAULT_THEME = "system"
    
    // UI settings
    const val ANIMATION_DURATION = 300L
    const val BUTTON_PRESS_DURATION = 100L
    
    // Validation settings
    const val MIN_EMI_AMOUNT = 1000.0
    const val MAX_EMI_AMOUNT = 10000000.0
    const val MIN_INTEREST_RATE = 0.0
    const val MAX_INTEREST_RATE = 50.0
    const val MIN_LOAN_TENURE = 1.0
    const val MAX_LOAN_TENURE = 30.0
    
    const val MIN_BMI_WEIGHT = 1.0
    const val MAX_BMI_WEIGHT = 500.0
    const val MIN_BMI_HEIGHT = 50.0
    const val MAX_BMI_HEIGHT = 300.0
    
    const val MIN_AGE_YEARS = 0
    const val MAX_AGE_YEARS = 150
    
    // Currency settings
    const val DEFAULT_CURRENCY = "USD"
    const val CURRENCY_UPDATE_INTERVAL = 3600000L // 1 hour
    
    // Unit conversion settings
    const val PRECISION_LENGTH = 4
    const val PRECISION_WEIGHT = 4
    const val PRECISION_TEMPERATURE = 2
    const val PRECISION_AREA = 4
    const val PRECISION_VOLUME = 4
    
    // Error messages
    const val ERROR_NETWORK = "Network error. Please check your connection."
    const val ERROR_INVALID_CURRENCY = "Invalid currency code"
    const val ERROR_CURRENCY_UPDATE_FAILED = "Failed to update currency rates"
    
    // Success messages
    const val SUCCESS_CURRENCY_UPDATED = "Currency rates updated successfully"
    const val SUCCESS_CALCULATION_SAVED = "Calculation saved to history"
    const val SUCCESS_MEMORY_CLEARED = "Memory cleared successfully"
}

