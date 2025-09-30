package com.example.calculator.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.calculator.constants.CalculatorConstants

/**
 * Manager class for handling theme operations
 */
class ThemeManager private constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        CalculatorConstants.PREFS_NAME, 
        Context.MODE_PRIVATE
    )
    
    companion object {
        @Volatile
        private var INSTANCE: ThemeManager? = null
        
        fun getInstance(context: Context): ThemeManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    /**
     * Apply theme based on system settings
     */
    fun applySystemTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
    /**
     * Apply light theme
     */
    fun applyLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        saveTheme("light")
    }
    
    /**
     * Apply dark theme
     */
    fun applyDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        saveTheme("dark")
    }
    
    /**
     * Apply colorful theme
     */
    fun applyColorfulTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        saveTheme("colorful")
    }
    
    /**
     * Get current theme
     */
    fun getCurrentTheme(): String {
        return prefs.getString(CalculatorConstants.KEY_THEME, "system") ?: "system"
    }
    
    /**
     * Save theme preference
     */
    private fun saveTheme(theme: String) {
        prefs.edit().putString(CalculatorConstants.KEY_THEME, theme).apply()
    }
    
    /**
     * Apply saved theme
     */
    fun applySavedTheme() {
        when (getCurrentTheme()) {
            "light" -> applyLightTheme()
            "dark" -> applyDarkTheme()
            "colorful" -> applyColorfulTheme()
            else -> applySystemTheme()
        }
    }
}

