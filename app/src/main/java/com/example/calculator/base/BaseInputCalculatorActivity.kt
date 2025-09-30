package com.example.calculator.base

import android.widget.EditText
import android.widget.TextView
import com.example.calculator.R

/**
 * Base class for calculators that use input fields
 * Provides common functionality for input-based calculators
 */
abstract class BaseInputCalculatorActivity : BaseCalculatorActivity() {
    
    protected lateinit var calculateButton: TextView
    protected lateinit var resultText: TextView
    
    override fun initializeViews() {
        super.initializeViews()
        calculateButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)
    }
    
    override fun setupClickListeners() {
        super.setupClickListeners()
        calculateButton.setOnClickListener { onCalculateClick() }
    }
    
    /**
     * Handle calculate button click
     */
    protected abstract fun onCalculateClick()
    
    /**
     * Validate input fields
     */
    protected fun validateInputs(vararg inputs: EditText): Boolean {
        for (input in inputs) {
            if (input.text.toString().trim().isEmpty()) {
                showError("Please fill in all required fields")
                return false
            }
        }
        return true
    }
    
    /**
     * Show error message
     */
    protected fun showError(message: String) {
        resultText.text = message
    }
    
    /**
     * Show result
     */
    protected fun showResult(result: String) {
        resultText.text = result
    }
    
    /**
     * Clear all input fields
     */
    protected fun clearInputs(vararg inputs: EditText) {
        for (input in inputs) {
            input.text.clear()
        }
    }
}

