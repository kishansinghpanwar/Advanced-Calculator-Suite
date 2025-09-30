package com.example.calculator.managers

import android.content.Context
import android.content.SharedPreferences
import com.example.calculator.constants.CalculatorConstants
import org.json.JSONArray
import org.json.JSONObject

/**
 * Manager class for handling calculation history
 */
class HistoryManager private constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        CalculatorConstants.PREFS_NAME, 
        Context.MODE_PRIVATE
    )
    
    companion object {
        @Volatile
        private var INSTANCE: HistoryManager? = null
        
        fun getInstance(context: Context): HistoryManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    /**
     * Save a calculation to history
     */
    fun saveCalculation(expression: String, result: String, timestamp: Long = System.currentTimeMillis()) {
        val history = getHistory()
        val calculation = JSONObject().apply {
            put("expression", expression)
            put("result", result)
            put("timestamp", timestamp)
        }
        
        history.put(calculation)
        
        // Keep only last 100 calculations
        if (history.length() > 100) {
            val newHistory = JSONArray()
            for (i in 1 until history.length()) {
                newHistory.put(history.get(i))
            }
            saveHistory(newHistory)
        } else {
            saveHistory(history)
        }
    }
    
    /**
     * Get calculation history
     */
    fun getHistory(): JSONArray {
        val historyString = prefs.getString(CalculatorConstants.KEY_HISTORY, "[]")
        return try {
            JSONArray(historyString)
        } catch (e: Exception) {
            JSONArray()
        }
    }
    
    /**
     * Save history to preferences
     */
    private fun saveHistory(history: JSONArray) {
        prefs.edit().putString(CalculatorConstants.KEY_HISTORY, history.toString()).apply()
    }
    
    /**
     * Clear all history
     */
    fun clearHistory() {
        prefs.edit().remove(CalculatorConstants.KEY_HISTORY).apply()
    }
    
    /**
     * Get history as list of Calculation objects
     */
    fun getHistoryList(): List<Calculation> {
        val history = getHistory()
        val calculations = mutableListOf<Calculation>()
        
        for (i in 0 until history.length()) {
            try {
                val calculation = history.getJSONObject(i)
                calculations.add(
                    Calculation(
                        expression = calculation.getString("expression"),
                        result = calculation.getString("result"),
                        timestamp = calculation.getLong("timestamp")
                    )
                )
            } catch (e: Exception) {
                // Skip invalid entries
            }
        }
        
        return calculations.reversed() // Most recent first
    }
    
    /**
     * Data class for calculation history
     */
    data class Calculation(
        val expression: String,
        val result: String,
        val timestamp: Long
    )
}

