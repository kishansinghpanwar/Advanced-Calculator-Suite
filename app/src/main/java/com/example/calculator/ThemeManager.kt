package com.example.calculator

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {
    const val THEME_LIGHT = "light"
    const val THEME_DARK = "dark"
    const val THEME_COLORFUL = "colorful"
    
    fun applyTheme(context: Context, theme: String) {
        val prefs = context.getSharedPreferences("calculator_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("theme", theme).apply()
        
        when (theme) {
            THEME_DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            THEME_LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            THEME_COLORFUL -> {
                // For now, use dark theme for colorful
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
    
    fun getCurrentTheme(context: Context): String {
        val prefs = context.getSharedPreferences("calculator_prefs", Context.MODE_PRIVATE)
        return prefs.getString("theme", THEME_LIGHT) ?: THEME_LIGHT
    }
    
    fun isDarkTheme(context: Context): Boolean {
        return when (getCurrentTheme(context)) {
            THEME_DARK, THEME_COLORFUL -> true
            else -> false
        }
    }
}
