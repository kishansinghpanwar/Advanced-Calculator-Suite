package com.example.calculator.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.base.BaseCalculatorActivity
import com.example.calculator.constants.CalculatorConstants
import com.example.calculator.managers.HistoryManager
import com.example.calculator.managers.MemoryManager
import com.example.calculator.utils.CalculatorUtils
import com.example.calculator.utils.ValidationUtils

/**
 * Refactored Main Activity for Normal Calculator
 * Uses the new structured approach with base classes and managers
 */
class RefactoredMainActivity : BaseCalculatorActivity() {
    
    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView
    
    // Calculator state
    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private var result = 0.0
    private var isNewOperation = true
    
    // Managers
    private lateinit var historyManager: HistoryManager
    private lateinit var memoryManager: MemoryManager
    
    override fun getLayoutResource(): Int = R.layout.activity_main
    
    override fun initializeViews() {
        super.initializeViews()
        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)
        
        // Initialize managers
        historyManager = HistoryManager.getInstance(this)
        memoryManager = MemoryManager.getInstance(this)
    }
    
    override fun setupCalculator() {
        // Setup calculator-specific functionality
        updateDisplay()
    }
    
    override fun setupClickListeners() {
        super.setupClickListeners()
        // Number buttons
        findViewById<TextView>(R.id.btn0).setOnClickListener { onNumberClick("0") }
        findViewById<TextView>(R.id.btn1).setOnClickListener { onNumberClick("1") }
        findViewById<TextView>(R.id.btn2).setOnClickListener { onNumberClick("2") }
        findViewById<TextView>(R.id.btn3).setOnClickListener { onNumberClick("3") }
        findViewById<TextView>(R.id.btn4).setOnClickListener { onNumberClick("4") }
        findViewById<TextView>(R.id.btn5).setOnClickListener { onNumberClick("5") }
        findViewById<TextView>(R.id.btn6).setOnClickListener { onNumberClick("6") }
        findViewById<TextView>(R.id.btn7).setOnClickListener { onNumberClick("7") }
        findViewById<TextView>(R.id.btn8).setOnClickListener { onNumberClick("8") }
        findViewById<TextView>(R.id.btn9).setOnClickListener { onNumberClick("9") }
        
        // Operator buttons
        findViewById<TextView>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<TextView>(R.id.btnSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<TextView>(R.id.btnMultiply).setOnClickListener { onOperatorClick("×") }
        findViewById<TextView>(R.id.btnDivide).setOnClickListener { onOperatorClick("÷") }
        
        // Function buttons
        findViewById<TextView>(R.id.btnClearAll).setOnClickListener { onClearAllClick() }
        findViewById<TextView>(R.id.btnDelete).setOnClickListener { onDeleteClick() }
        findViewById<TextView>(R.id.btnDecimal).setOnClickListener { onDecimalClick() }
        findViewById<TextView>(R.id.btnPlusMinus).setOnClickListener { onPlusMinusClick() }
        findViewById<TextView>(R.id.btnPercent).setOnClickListener { onPercentClick() }
        
        // History button
        findViewById<TextView>(R.id.btnHistory).setOnClickListener { onHistoryClick() }
        
        // Equals button
        findViewById<TextView>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
    }
    
    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentNumber = number
            isNewOperation = false
        } else {
            currentNumber += number
        }
        updateDisplay()
    }
    
    private fun onOperatorClick(op: String) {
        if (currentNumber.isNotEmpty()) {
            if (previousNumber.isNotEmpty() && operator.isNotEmpty()) {
                calculateResult()
            }
            previousNumber = currentNumber
            currentNumber = ""
            operator = op
            updateDisplay()
        }
    }
    
    private fun onEqualsClick() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operator.isNotEmpty()) {
            calculateResult()
            isNewOperation = true
        }
    }
    
    private fun calculateResult() {
        val prev = previousNumber.toDoubleOrNull() ?: 0.0
        val current = currentNumber.toDoubleOrNull() ?: 0.0
        
        result = when (operator) {
            "+" -> prev + current
            "-" -> prev - current
            "×" -> prev * current
            "÷" -> CalculatorUtils.safeDivide(prev, current)
            else -> current
        }
        
        if (!CalculatorUtils.isValidNumber(result)) {
            currentNumber = CalculatorConstants.ERROR_DIVISION_BY_ZERO
        } else {
            currentNumber = CalculatorUtils.formatScientific(result)
        }
        
        // Save to history
        val expression = "$previousNumber $operator $currentNumber"
        historyManager.saveCalculation(expression, currentNumber)
        
        previousNumber = ""
        operator = ""
        updateDisplay()
    }
    
    private fun onClearAllClick() {
        currentNumber = ""
        previousNumber = ""
        operator = ""
        result = 0.0
        isNewOperation = true
        updateDisplay()
    }
    
    private fun onDeleteClick() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            updateDisplay()
        }
    }
    
    private fun onDecimalClick() {
        if (currentNumber.isEmpty()) {
            currentNumber = "0."
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
        }
        updateDisplay()
    }
    
    private fun onPlusMinusClick() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.substring(1)
            } else {
                "-$currentNumber"
            }
            updateDisplay()
        }
    }
    
    private fun onPercentClick() {
        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toDoubleOrNull() ?: 0.0
            currentNumber = CalculatorUtils.formatScientific(number / 100)
            updateDisplay()
        }
    }
    
    private fun onHistoryClick() {
        val intent = Intent(this, com.example.calculator.HistoryActivity::class.java)
        startActivity(intent)
    }
    
    private fun updateDisplay() {
        tvResult.text = currentNumber.ifEmpty { "0" }
        
        val expression = if (previousNumber.isNotEmpty() && operator.isNotEmpty()) {
            "$previousNumber $operator"
        } else {
            ""
        }
        tvExpression.text = expression
        tvExpression.visibility = if (expression.isNotEmpty()) View.VISIBLE else View.GONE
    }
}
