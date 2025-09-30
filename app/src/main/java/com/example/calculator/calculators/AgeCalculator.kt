package com.example.calculator.calculators

import com.example.calculator.utils.CalculatorUtils
import java.util.*

/**
 * Age Calculator implementation
 */
object AgeCalculator {
    
    /**
     * Calculate age from birth date and current date
     */
    fun calculateAge(birthDate: Date, currentDate: Date): AgeCalculationResult {
        if (birthDate.after(currentDate)) {
            return AgeCalculationResult.error("Birth date cannot be after current date")
        }
        
        val calendarBirth = Calendar.getInstance()
        val calendarCurrent = Calendar.getInstance()
        calendarBirth.time = birthDate
        calendarCurrent.time = currentDate
        
        var years = calendarCurrent.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR)
        var months = calendarCurrent.get(Calendar.MONTH) - calendarBirth.get(Calendar.MONTH)
        var days = calendarCurrent.get(Calendar.DAY_OF_MONTH) - calendarBirth.get(Calendar.DAY_OF_MONTH)
        
        if (days < 0) {
            months--
            val daysInPreviousMonth = calendarCurrent.getActualMaximum(Calendar.DAY_OF_MONTH)
            days += daysInPreviousMonth
        }
        
        if (months < 0) {
            years--
            months += 12
        }
        
        val totalDays = ((currentDate.time - birthDate.time) / (1000 * 60 * 60 * 24)).toInt()
        val totalWeeks = totalDays / 7
        val totalMonths = years * 12 + months
        
        return AgeCalculationResult(
            years = years,
            months = months,
            days = days,
            totalDays = totalDays,
            totalWeeks = totalWeeks,
            totalMonths = totalMonths
        )
    }
    
    /**
     * Data class for age calculation result
     */
    data class AgeCalculationResult(
        val years: Int,
        val months: Int,
        val days: Int,
        val totalDays: Int,
        val totalWeeks: Int,
        val totalMonths: Int,
        val error: String? = null
    ) {
        fun isError(): Boolean = error != null
        
        fun getFormattedAge(): String = "$years years, $months months, $days days"
        fun getFormattedTotalDays(): String = totalDays.toString()
        fun getFormattedTotalWeeks(): String = totalWeeks.toString()
        fun getFormattedTotalMonths(): String = totalMonths.toString()
        
        companion object {
            fun error(message: String): AgeCalculationResult {
                return AgeCalculationResult(0, 0, 0, 0, 0, 0, message)
            }
        }
    }
}

