package com.example.calculator.managers

import android.content.Context
import android.content.SharedPreferences
import com.example.calculator.constants.CalculatorConstants

/**
 * Manager class for handling calculator memory operations
 */
class MemoryManager private constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        CalculatorConstants.PREFS_NAME, 
        Context.MODE_PRIVATE
    )
    
    companion object {
        @Volatile
        private var INSTANCE: MemoryManager? = null
        
        fun getInstance(context: Context): MemoryManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MemoryManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    /**
     * Get current memory value
     */
    fun getMemory(): Double {
        return prefs.getFloat(CalculatorConstants.KEY_MEMORY, 0f).toDouble()
    }
    
    /**
     * Set memory value
     */
    fun setMemory(value: Double) {
        prefs.edit().putFloat(CalculatorConstants.KEY_MEMORY, value.toFloat()).apply()
    }
    
    /**
     * Add value to memory
     */
    fun addToMemory(value: Double) {
        val currentMemory = getMemory()
        setMemory(currentMemory + value)
    }
    
    /**
     * Subtract value from memory
     */
    fun subtractFromMemory(value: Double) {
        val currentMemory = getMemory()
        setMemory(currentMemory - value)
    }
    
    /**
     * Clear memory
     */
    fun clearMemory() {
        prefs.edit().remove(CalculatorConstants.KEY_MEMORY).apply()
    }
    
    /**
     * Check if memory has a value
     */
    fun hasMemory(): Boolean {
        return getMemory() != 0.0
    }
}

