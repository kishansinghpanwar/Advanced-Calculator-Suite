package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class ScientificCalculatorActivity : AppCompatActivity() {
    
    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView
    
    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private var result = 0.0
    private var isNewOperation = true
    private var memory = 0.0
    
    private val decimalFormat = DecimalFormat("#.##########")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific_calculator)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)
    }
    
    private fun setupClickListeners() {
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
        
        // Header back button
        findViewById<ImageButton>(R.id.backButton).setOnClickListener { onBackClick() }
        
        // Equals button
        findViewById<TextView>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        
        // Scientific function buttons
        findViewById<TextView>(R.id.btnSin).setOnClickListener { onScientificFunctionClick("sin") }
        findViewById<TextView>(R.id.btnCos).setOnClickListener { onScientificFunctionClick("cos") }
        findViewById<TextView>(R.id.btnTan).setOnClickListener { onScientificFunctionClick("tan") }
        findViewById<TextView>(R.id.btnAsin).setOnClickListener { onScientificFunctionClick("asin") }
        findViewById<TextView>(R.id.btnAcos).setOnClickListener { onScientificFunctionClick("acos") }
        findViewById<TextView>(R.id.btnAtan).setOnClickListener { onScientificFunctionClick("atan") }
        findViewById<TextView>(R.id.btnLn).setOnClickListener { onScientificFunctionClick("ln") }
        findViewById<TextView>(R.id.btnLog).setOnClickListener { onScientificFunctionClick("log") }
        findViewById<TextView>(R.id.btnSqrt).setOnClickListener { onScientificFunctionClick("sqrt") }
        findViewById<TextView>(R.id.btnCbrt).setOnClickListener { onScientificFunctionClick("cbrt") }
        findViewById<TextView>(R.id.btnPower).setOnClickListener { onScientificFunctionClick("power") }
        findViewById<TextView>(R.id.btnFactorial).setOnClickListener { onScientificFunctionClick("!") }
        findViewById<TextView>(R.id.btnSquare).setOnClickListener { onScientificFunctionClick("x²") }
        findViewById<TextView>(R.id.btnCube).setOnClickListener { onScientificFunctionClick("x³") }
        findViewById<TextView>(R.id.btnInverse).setOnClickListener { onScientificFunctionClick("1/x") }
        findViewById<TextView>(R.id.btnPi).setOnClickListener { onScientificFunctionClick("π") }
        findViewById<TextView>(R.id.btnE).setOnClickListener { onScientificFunctionClick("e") }
        findViewById<TextView>(R.id.btnAbs).setOnClickListener { onScientificFunctionClick("abs") }
        
        // Memory buttons
        findViewById<TextView>(R.id.btnMC).setOnClickListener { onMemoryClick("MC") }
        findViewById<TextView>(R.id.btnMR).setOnClickListener { onMemoryClick("MR") }
        findViewById<TextView>(R.id.btnMS).setOnClickListener { onMemoryClick("MS") }
        findViewById<TextView>(R.id.btnMPlus).setOnClickListener { onMemoryClick("M+") }
        findViewById<TextView>(R.id.btnMMinus).setOnClickListener { onMemoryClick("M-") }
        
        // History and Converter buttons
        findViewById<TextView>(R.id.btnHistory).setOnClickListener { onHistoryClick() }
        findViewById<TextView>(R.id.btnConverter).setOnClickListener { onConverterClick() }
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
            "÷" -> if (current != 0.0) prev / current else Double.NaN
            else -> current
        }
        
        if (result.isNaN() || result.isInfinite()) {
            currentNumber = "Error"
        } else {
            currentNumber = decimalFormat.format(result)
        }
        
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
            currentNumber = decimalFormat.format(number / 100)
            updateDisplay()
        }
    }
    
    private fun onBackClick() {
        finish()
    }
    
    private fun onScientificFunctionClick(function: String) {
        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toDoubleOrNull() ?: 0.0
            val result = when (function) {
                "sin" -> kotlin.math.sin(Math.toRadians(number))
                "cos" -> kotlin.math.cos(Math.toRadians(number))
                "tan" -> kotlin.math.tan(Math.toRadians(number))
                "asin" -> Math.toDegrees(kotlin.math.asin(number))
                "acos" -> Math.toDegrees(kotlin.math.acos(number))
                "atan" -> Math.toDegrees(kotlin.math.atan(number))
                "ln" -> kotlin.math.ln(number)
                "log" -> kotlin.math.log10(number)
                "sqrt" -> kotlin.math.sqrt(number)
                "cbrt" -> kotlin.math.cbrt(number)
                "power" -> Math.pow(number, 2.0)
                "!" -> factorial(number.toInt().toDouble())
                "x²" -> number * number
                "x³" -> number * number * number
                "1/x" -> 1.0 / number
                "π" -> kotlin.math.PI
                "e" -> kotlin.math.E
                "abs" -> kotlin.math.abs(number)
                else -> number
            }
            
            if (result.isNaN() || result.isInfinite()) {
                currentNumber = "Error"
            } else {
                currentNumber = decimalFormat.format(result)
            }
            updateDisplay()
        }
    }
    
    private fun onMemoryClick(operation: String) {
        when (operation) {
            "MC" -> memory = 0.0
            "MR" -> {
                currentNumber = decimalFormat.format(memory)
                updateDisplay()
            }
            "MS" -> {
                if (currentNumber.isNotEmpty()) {
                    memory = currentNumber.toDoubleOrNull() ?: 0.0
                }
            }
            "M+" -> {
                if (currentNumber.isNotEmpty()) {
                    memory += currentNumber.toDoubleOrNull() ?: 0.0
                }
            }
            "M-" -> {
                if (currentNumber.isNotEmpty()) {
                    memory -= currentNumber.toDoubleOrNull() ?: 0.0
                }
            }
        }
    }
    
    private fun onHistoryClick() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
    
    private fun onConverterClick() {
        val intent = Intent(this, UnitConverterActivity::class.java)
        startActivity(intent)
    }
    
    private fun factorial(n: Double): Double {
        if (n < 0 || n != n.toInt().toDouble()) return Double.NaN
        if (n <= 1) return 1.0
        var result = 1.0
        for (i in 2..n.toInt()) {
            result *= i
        }
        return result
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
