package com.example.calculator.base

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.R

/**
 * Base class for all calculator activities
 * Provides common functionality and UI elements
 */
abstract class BaseCalculatorActivity : AppCompatActivity() {
    
    protected lateinit var backButton: ImageButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        
        initializeViews()
        setupClickListeners()
        setupCalculator()
    }
    
    /**
     * Get the layout resource for the specific calculator
     */
    protected abstract fun getLayoutResource(): Int
    
    /**
     * Initialize common views
     */
    protected open fun initializeViews() {
        backButton = findViewById(R.id.backButton)
    }
    
    /**
     * Setup common click listeners
     */
    protected open fun setupClickListeners() {
        backButton.setOnClickListener { onBackClick() }
    }
    
    /**
     * Setup calculator-specific functionality
     */
    protected abstract fun setupCalculator()
    
    /**
     * Handle back button click
     */
    protected open fun onBackClick() {
        finish()
    }
}
