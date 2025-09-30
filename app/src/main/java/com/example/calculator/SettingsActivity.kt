package com.example.calculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var themeGroup: RadioGroup
    private lateinit var btnBack: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        initializeViews()
        setupClickListeners()
        loadCurrentTheme()
    }
    
    private fun initializeViews() {
        themeGroup = findViewById(R.id.themeGroup)
        btnBack = findViewById(R.id.btnBack)
    }
    
    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioDark -> setTheme("dark")
                R.id.radioLight -> setTheme("light")
                R.id.radioColorful -> setTheme("colorful")
            }
        }
    }
    
    private fun loadCurrentTheme() {
        val currentTheme = ThemeManager.getCurrentTheme(this)
        
        when (currentTheme) {
            ThemeManager.THEME_DARK -> themeGroup.check(R.id.radioDark)
            ThemeManager.THEME_LIGHT -> themeGroup.check(R.id.radioLight)
            ThemeManager.THEME_COLORFUL -> themeGroup.check(R.id.radioColorful)
        }
    }
    
    private fun setTheme(theme: String) {
        ThemeManager.applyTheme(this, theme)
        recreate() // Restart activity to apply theme
    }
}
