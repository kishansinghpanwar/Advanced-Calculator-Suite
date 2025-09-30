package com.example.calculator.constants

/**
 * Constants used throughout the calculator app
 */
object CalculatorConstants {
    
    // Calculator types
    const val CALCULATOR_NORMAL = "normal"
    const val CALCULATOR_SCIENTIFIC = "scientific"
    const val CALCULATOR_EMI = "emi"
    const val CALCULATOR_FINANCE = "finance"
    const val CALCULATOR_UNIT = "unit"
    const val CALCULATOR_CURRENCY = "currency"
    const val CALCULATOR_PERCENTAGE = "percentage"
    const val CALCULATOR_TIP = "tip"
    const val CALCULATOR_AGE = "age"
    const val CALCULATOR_BMI = "bmi"
    
    // Intent extras
    const val EXTRA_CALCULATOR_MODE = "calculator_mode"
    const val EXTRA_CALCULATION_TYPE = "calculation_type"
    
    // SharedPreferences keys
    const val PREFS_NAME = "calculator_prefs"
    const val KEY_THEME = "theme"
    const val KEY_HISTORY = "calculation_history"
    const val KEY_MEMORY = "memory_value"
    
    // Calculation types
    const val CALC_TYPE_PERCENTAGE_OF = "percentage_of"
    const val CALC_TYPE_PERCENTAGE_CHANGE = "percentage_change"
    const val CALC_TYPE_PERCENTAGE_INCREASE = "percentage_increase"
    const val CALC_TYPE_PERCENTAGE_DECREASE = "percentage_decrease"
    
    const val CALC_TYPE_PRESENT_VALUE = "present_value"
    const val CALC_TYPE_FUTURE_VALUE = "future_value"
    const val CALC_TYPE_INTEREST_RATE = "interest_rate"
    const val CALC_TYPE_TIME_PERIOD = "time_period"
    
    // Unit categories
    const val UNIT_LENGTH = "length"
    const val UNIT_WEIGHT = "weight"
    const val UNIT_TEMPERATURE = "temperature"
    const val UNIT_AREA = "area"
    const val UNIT_VOLUME = "volume"
    const val UNIT_TIME = "time"
    
    // BMI categories
    const val BMI_UNDERWEIGHT = "Underweight"
    const val BMI_NORMAL = "Normal weight"
    const val BMI_OVERWEIGHT = "Overweight"
    const val BMI_OBESE = "Obese"
    
    // BMI ranges
    const val BMI_UNDERWEIGHT_MAX = 18.5
    const val BMI_NORMAL_MAX = 24.9
    const val BMI_OVERWEIGHT_MAX = 29.9
    
    // Error messages
    const val ERROR_INVALID_INPUT = "Invalid input"
    const val ERROR_DIVISION_BY_ZERO = "Cannot divide by zero"
    const val ERROR_INVALID_DATE = "Invalid date format"
    const val ERROR_CALCULATION_FAILED = "Calculation failed"
    
    // Success messages
    const val SUCCESS_CALCULATION_COMPLETE = "Calculation complete"
    const val SUCCESS_HISTORY_CLEARED = "History cleared"
    const val SUCCESS_MEMORY_CLEARED = "Memory cleared"
}

