package com.example.calculator

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

class HistoryManager(private val context: Context) {
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("calculator_history", Context.MODE_PRIVATE)
    private val historyKey = "calculation_history"
    private val maxHistorySize = 50
    
    fun addCalculation(expression: String, result: String) {
        val history = getHistory().toMutableList()
        
        // Add new calculation to the beginning
        history.add(0, "$expression = $result")
        
        // Keep only the last maxHistorySize calculations
        if (history.size > maxHistorySize) {
            history.removeAt(history.size - 1)
        }
        
        saveHistory(history)
    }
    
    fun getHistory(): List<String> {
        val historyJson = sharedPreferences.getString(historyKey, "[]") ?: "[]"
        val jsonArray = JSONArray(historyJson)
        val history = mutableListOf<String>()
        
        for (i in 0 until jsonArray.length()) {
            history.add(jsonArray.getString(i))
        }
        
        return history
    }
    
    fun clearHistory() {
        sharedPreferences.edit().remove(historyKey).apply()
    }
    
    private fun saveHistory(history: List<String>) {
        val jsonArray = JSONArray()
        for (item in history) {
            jsonArray.put(item)
        }
        sharedPreferences.edit().putString(historyKey, jsonArray.toString()).apply()
    }
}

